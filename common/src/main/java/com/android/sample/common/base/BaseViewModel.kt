package com.android.sample.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.sample.common.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

open class BaseViewModel<T>(
    private val repository: BaseRepository<T>
) : ViewModel() {

    private val _stateFlow = MutableStateFlow<Resource<List<T>>>(Resource.Loading)
    val stateFlow: StateFlow<Resource<List<T>>>
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