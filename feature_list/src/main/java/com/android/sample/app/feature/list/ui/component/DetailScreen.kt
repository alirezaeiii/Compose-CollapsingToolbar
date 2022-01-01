package com.android.sample.app.feature.list.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.android.sample.app.feature.list.ui.common.Image
import com.android.sample.core.response.Poster

@Composable
fun DetailScreen(item: Poster, pressOnBack: () -> Unit, sendNotification: () -> Unit) {
    val typography = MaterialTheme.typography
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxHeight()
    ) {
        Box {
            Image(url = item.poster, height = 420.dp)
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                tint = Color.White,
                contentDescription = null,
                modifier = Modifier
                    .padding(12.dp)
                    .clickable(onClick = pressOnBack)
            )
        }
        Spacer(Modifier.height(8.dp))
        Button(
            onClick = sendNotification,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "DeepLink")

        }
        Spacer(Modifier.height(8.dp))
        Text(
            modifier = Modifier.padding(8.dp),
            text = item.description,
            style = typography.body2,
            color = MaterialTheme.colors.onSurface
        )
    }
}