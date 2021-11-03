package com.android.sample.app.feature.list.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.android.sample.app.feature.list.ui.OnClickListener
import com.android.sample.core.response.Poster

@ExperimentalCoilApi
@Composable
fun VerticalListItem(item: Poster, callback: OnClickListener) {
    val typography = MaterialTheme.typography
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(onClick = { callback.onClick(item) })
    ) {
        ImageView(url = item.poster, height = 150.dp)
        Spacer(Modifier.height(16.dp))
        Text(
            text = item.name,
            style = typography.h6
        )
        Text(
            text = item.release,
            style = typography.body2
        )
        Text(
            text = item.playtime,
            style = typography.subtitle2
        )
    }
}