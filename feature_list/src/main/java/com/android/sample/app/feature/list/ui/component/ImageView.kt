package com.android.sample.app.feature.list.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import coil.compose.rememberImagePainter

@Composable
fun ImageView(url: String, height: Dp) {
    val imageModifier = Modifier
        .height(height)
        .fillMaxWidth()
        .clip(shape = MaterialTheme.shapes.medium)

    Image(
        painter = rememberImagePainter(url),
        contentDescription = null,
        modifier = imageModifier,
        contentScale = ContentScale.Crop,
    )
}