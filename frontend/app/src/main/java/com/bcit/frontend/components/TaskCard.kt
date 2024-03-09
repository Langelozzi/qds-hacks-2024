package com.bcit.frontend.components

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskCard(
    positionState: AnchoredDraggableState<DragAnchors>
) {
    SwipeableCard(
        draggableCardState = positionState,
        onSwipeLeft = { Log.d("Swipe", "Left") },
        onSwipeRight = { Log.d("Swipe", "Right") },
    ) {
        Column {
            Text("Task 1")
            Button(onClick = { /*TODO*/ }) {
                Text("Complete")
            }
        }
    }
}
