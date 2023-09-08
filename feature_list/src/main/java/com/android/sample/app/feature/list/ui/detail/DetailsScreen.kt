package com.android.sample.app.feature.list.ui.detail

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.android.sample.core.response.Poster

@Composable
fun DetailsScreen(
    item: Poster,
    pressOnBack: () -> Unit,
    sendNotification: () -> Unit
) {
    val scrollState = rememberScrollState()
    var detailScroller by remember {
        mutableStateOf(DetailsScroller(scrollState, Float.MIN_VALUE))
    }

    val transitionState =
        remember(detailScroller) { detailScroller.toolbarTransitionState }
    val toolbarState = detailScroller.getToolbarState(LocalDensity.current)

    // Transition that fades in/out the header with the image and the Toolbar
    val transition = updateTransition(transitionState, label = "")
    val contentAlpha = transition.animateFloat(
        transitionSpec = { spring(stiffness = Spring.StiffnessLow) }, label = ""
    ) { toolbarTransitionState ->
        if (toolbarTransitionState == ToolbarState.HIDDEN) 1f else 0f
    }

    val toolbarHeightPx = with(LocalDensity.current) {
        278.dp.roundToPx().toFloat()
    }
    val toolbarOffsetHeightPx = remember { mutableStateOf(0f) }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newOffset = toolbarOffsetHeightPx.value + delta
                toolbarOffsetHeightPx.value = newOffset.coerceIn(-toolbarHeightPx, 0f)
                return Offset.Zero
            }
        }
    }

    Box(
        Modifier
            .fillMaxSize()
            // attach as a parent to the nested scroll system
            .nestedScroll(nestedScrollConnection)
    ) {
        DetailsContent(
            scrollState = scrollState,
            onNamePosition = { newNamePosition ->
                // Comparing to Float.MIN_VALUE as we are just interested on the original
                // position of name on the screen
                if (detailScroller.namePosition == Float.MIN_VALUE) {
                    detailScroller =
                        detailScroller.copy(namePosition = newNamePosition)
                }
            },
            item = item,
            boxHeight = with(LocalDensity.current) {
                440.dp + toolbarOffsetHeightPx.value.toDp()
            },
            sendNotification = sendNotification,
            contentAlpha = { contentAlpha.value }
        )
        DetailsToolbar(
            toolbarState, item.name, pressOnBack,
            contentAlpha = { contentAlpha.value }
        )
    }
}

@Composable
private fun DetailsContent(
    scrollState: ScrollState,
    onNamePosition: (Float) -> Unit,
    item: Poster,
    boxHeight: Dp,
    sendNotification: () -> Unit,
    contentAlpha: () -> Float,
) {
    val typography = MaterialTheme.typography
    Column(
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        Box(
            modifier = Modifier.height(boxHeight)
        ) {
            Image(
                painter = rememberImagePainter(item.poster),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .clip(shape = MaterialTheme.shapes.medium)
                    .alpha(contentAlpha()),
                contentScale = ContentScale.Crop,
            )
            Button(
                onClick = sendNotification,
                shape = RoundedCornerShape(36.dp),
                modifier = Modifier
                    .width(126.dp)
                    .align(Alignment.BottomCenter)
                    .offset(y = 23.5.dp)

            ) {
                Text(text = "DeepLink")
            }
        }
        Spacer(Modifier.height(32.dp)
            .onGloballyPositioned { onNamePosition(it.positionInWindow().y) })
        Text(
            modifier = Modifier.padding(8.dp),
            text = item.description,
            style = typography.body2,
            fontSize = 18.sp,
            color = MaterialTheme.colors.onSurface
        )
        Text(
            modifier = Modifier.padding(8.dp),
            text = item.plot,
            style = typography.body1,
            fontSize = 18.sp,
            color = MaterialTheme.colors.onSurface
        )
    }
}

@Composable
private fun DetailsToolbar(
    toolbarState: ToolbarState,
    name: String,
    pressOnBack: () -> Unit,
    contentAlpha: () -> Float
) {
    if (toolbarState.isShown) {
        DetailsToolbar(
            name = name,
            onBackClick = pressOnBack,
        )
    } else {
        HeaderActions(
            onBackClick = pressOnBack,
            modifier = Modifier.alpha(contentAlpha())
        )
    }
}

@Composable
private fun DetailsToolbar(
    name: String,
    onBackClick: () -> Unit
) {
    Surface {
        TopAppBar(
            backgroundColor = MaterialTheme.colors.surface
        ) {
            IconButton(
                onBackClick,
                Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "back"
                )
            }
            Text(
                text = name,
                style = MaterialTheme.typography.h6,
                // As title in TopAppBar has extra inset on the left, need to do this: b/158829169
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .wrapContentSize(Alignment.CenterStart)
            )
        }
    }
}

@Composable
private fun HeaderActions(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val iconModifier = Modifier
            .sizeIn(
                maxWidth = 32.dp,
                maxHeight = 32.dp
            )
            .background(
                color = MaterialTheme.colors.surface,
                shape = CircleShape
            )

        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .padding(start = 12.dp)
                .then(iconModifier)
        ) {
            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = "back",
                tint = MaterialTheme.colors.onSurface
            )
        }
    }
}