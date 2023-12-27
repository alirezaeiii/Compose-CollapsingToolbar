package com.android.sample.app.feature.list.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.android.sample.app.feature.list.R

object Dimens {

    val DetailAppBarHeight: Dp
        @Composable get() = dimensionResource(R.dimen.detail_app_bar_height)

    val DetailBoxImageHeight: Dp
        @Composable get() = dimensionResource(R.dimen.detail_box_image_height)
}