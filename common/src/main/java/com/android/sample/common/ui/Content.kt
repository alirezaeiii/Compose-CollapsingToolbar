package com.android.sample.common.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.android.sample.common.base.BaseViewModel
import com.android.sample.common.util.Resource
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun <T> Content(
    viewModel: BaseViewModel<T>,
    successScreen: @Composable (List<T>) -> Unit
) {
    when (val resource = viewModel.stateFlow.collectAsState().value) {
        is Resource.Loading -> ProgressScreen()
        is Resource.Success -> {
            val isRefreshing by viewModel.isRefreshing.collectAsState()
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
                onRefresh = { viewModel.refresh() },
            ) {
                successScreen(resource.data)
            }
        }
        is Resource.Error ->
            ErrorScreen(message = resource.message, viewModel::refresh)
    }
}