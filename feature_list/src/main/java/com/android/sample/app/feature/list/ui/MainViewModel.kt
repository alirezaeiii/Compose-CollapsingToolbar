package com.android.sample.app.feature.list.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.sample.common.base.BaseRepository
import com.android.sample.common.util.ViewState
import com.android.sample.core.response.Poster
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: BaseRepository<Poster>
) : ViewModel() {

    private val _stateFlow = MutableStateFlow<ViewState<List<Poster>>>(ViewState.Loading)
    val stateFlow: StateFlow<ViewState<List<Poster>>>
        get() = _stateFlow

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.emit(true)
            repository.result.collect {
                _stateFlow.tryEmit(it)
            }
            _isRefreshing.emit(false)
        }
    }
}