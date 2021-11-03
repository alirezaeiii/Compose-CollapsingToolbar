package com.android.sample.app.feature.list.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.android.sample.core.response.Poster

@ExperimentalCoilApi
@Composable
fun DetailView(item: Poster, pressOnBack: () -> Unit) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxHeight()
    ) {
        Box {
            ImageView(url = item.poster, height = 420.dp)
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                tint = Color.White,
                contentDescription = null,
                modifier = Modifier
                    .padding(12.dp)
                    .clickable(onClick = pressOnBack)
            )
        }
        Spacer(Modifier.height(16.dp))
        Text(
            modifier = Modifier.padding(8.dp),
            text = item.description,
            style = typography.body2
        )
    }
}