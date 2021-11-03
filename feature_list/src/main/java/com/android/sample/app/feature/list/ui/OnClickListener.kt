package com.android.sample.app.feature.list.ui

import com.android.sample.core.response.Poster

class OnClickListener(val clickListener: (poster: Poster) -> Unit) {
    fun onClick(poster: Poster) =
        clickListener(poster)
}