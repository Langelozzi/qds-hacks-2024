package com.bcit.frontend.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

enum class DragAnchors {
    Left,
    OnScreen,
    Right,
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SwipeableCard(
    draggableCardState: AnchoredDraggableState<DragAnchors>,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    onSwipeLeft: () -> Unit = {print("test")},
    onSwipeRight: () -> Unit = {},
    initialPosition: DragAnchors = DragAnchors.OnScreen,
    content: @Composable () -> Unit = {},
) {
    draggableCardState.apply {
        updateAnchors(
            DraggableAnchors {
                DragAnchors.Left at -1400f
                DragAnchors.OnScreen at 0f
                DragAnchors.Right at 1400f
            }
        )
    }

    LaunchedEffect(draggableCardState.currentValue) {
        when (draggableCardState.currentValue) {
            DragAnchors.Left -> onSwipeLeft()
            DragAnchors.Right -> onSwipeRight()
            else -> {}
        }
    }

    // Composable UI (JSX)
    Card(

        shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp),
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth()
            .offset {
                IntOffset(
                    x = draggableCardState
                        .requireOffset()
                        .roundToInt(),
                    y = 0,
                )
            }
            .anchoredDraggable(draggableCardState, Orientation.Horizontal)
            .clickable { onClick() }
            .border(BorderStroke( width = 10.dp, color = Color(0xFFFFE264)) ),
    ) {
        content()
    }
}
