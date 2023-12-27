package com.android.sample.app.feature.list.ui

import com.android.sample.common.base.BaseRepository
import com.android.sample.common.base.BaseViewModel
import com.android.sample.core.response.Poster
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(repository: BaseRepository<Poster>) :
    BaseViewModel<Poster>(repository)