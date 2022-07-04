package com.android.sample.app.feature.list.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.android.sample.core.response.Poster

@Composable
fun VerticalCollection(
    posters: List<Poster>,
    onPosterClick: (Poster) -> Unit
) {
    LazyColumn {
        items(
            items = posters,
            itemContent = { item ->
                VerticalListItem(item = item, onPosterClick = onPosterClick)
                ListItemDivider()
            }
        )
    }
}

@Composable
private fun VerticalListItem(
    item: Poster,
    onPosterClick: (Poster) -> Unit
) {
    val typography = MaterialTheme.typography
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(onClick = { onPosterClick(item) })
    ) {
        Image(
            painter = rememberImagePainter(item.poster),
            contentDescription = null,
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth()
                .clip(shape = MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop,
        )
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

@Composable
private fun ListItemDivider() {
    Divider(
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp),
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
    )
}