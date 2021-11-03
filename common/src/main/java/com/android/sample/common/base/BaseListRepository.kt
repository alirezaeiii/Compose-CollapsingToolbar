package com.android.sample.common.base

import android.content.Context

abstract class BaseListRepository<T>(
    context: Context
) : BaseRepository<List<T>>(context) {

    override fun isNotEmpty(t: List<T>): Boolean = t.isNotEmpty()
}