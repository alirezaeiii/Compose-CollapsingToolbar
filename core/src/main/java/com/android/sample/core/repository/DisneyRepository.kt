package com.android.sample.core.repository

import android.content.Context
import com.android.sample.common.base.BaseListRepository
import com.android.sample.core.database.DisneyDao
import com.android.sample.core.database.asDomainModel
import com.android.sample.core.network.DisneyService
import com.android.sample.core.response.Poster
import com.android.sample.core.response.asDatabaseModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DisneyRepository @Inject constructor(
    private val dao: DisneyDao,
    private val api: DisneyService,
    context: Context): BaseListRepository<Poster>(context) {

    override suspend fun query(): List<Poster> = dao.getPosters().asDomainModel()

    override suspend fun fetch(): List<Poster> = api.fetchDisneyPosterList()

    override suspend fun saveFetchResult(items: List<Poster>) {
        dao.insertAll(*items.asDatabaseModel())
    }
}