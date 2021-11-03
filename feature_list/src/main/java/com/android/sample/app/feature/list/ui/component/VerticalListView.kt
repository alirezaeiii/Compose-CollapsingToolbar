package com.android.sample.app.feature.list.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.android.sample.app.feature.list.ui.OnClickListener
import com.android.sample.core.response.Poster

@ExperimentalCoilApi
@Composable
fun VerticalListView(posters: List<Poster>, callback : OnClickListener) {
    LazyColumn {
        items(
            items = posters,
            itemContent = { item ->
                VerticalListItem(item = item, callback)
                ListItemDivider()
            }
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