package com.android.sample.common.base

import android.content.Context
import com.android.sample.common.R
import com.android.sample.common.util.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

abstract class BaseRepository<T>(
    context: Context,
    coroutinesDispatcher: CoroutineDispatcher
) {
    protected abstract suspend fun query(): List<T>

    protected abstract suspend fun fetch(): List<T>

    protected abstract suspend fun saveFetchResult(items: List<T>)

    val result: Flow<Resource<List<T>>> = flow {
        emit(Resource.Loading)
        val cache = query()
        if (cache.isNotEmpty()) {
            // ****** STEP 1: VIEW CACHE ******
            emit(Resource.Success(cache))
            try {
                // ****** STEP 2: MAKE NETWORK CALL, SAVE RESULT TO CACHE ******
                refresh()
                // ****** STEP 3: VIEW CACHE ******
                emit(Resource.Success(query()))
            } catch (t: Throwable) {
                Timber.e(t)
            }
        } else {
            try {
                // ****** STEP 1: MAKE NETWORK CALL, SAVE RESULT TO CACHE ******
                refresh()
                // ****** STEP 2: VIEW CACHE ******
                emit(Resource.Success(query()))
            } catch (t: Throwable) {
                emit(Resource.Error(context.getString(R.string.failed_refresh_msg)))
            }
        }
    }.flowOn(coroutinesDispatcher)

    private suspend fun refresh() {
        saveFetchResult(fetch())
    }
}