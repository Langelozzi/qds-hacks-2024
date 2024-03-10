package com.bcit.frontend.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DividerDefaults.color
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bcit.frontend.R
import com.bcit.frontend.dataClasses.Task
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskListItem(
    positionState: AnchoredDraggableState<DragAnchors>,
    task: Task,
    onUserChoice : (Task) -> Unit
) {
    SwipeableCard(
        draggableCardState = positionState,
        onSwipeLeft = { onUserChoice(task) },
        onSwipeRight = { onUserChoice(task) },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
//                .background(color = Color(0xFFFFBF69))
                .padding(15.dp)
        ) {
            Image(
                painter = painterResource(id = task.imageId),
                contentDescription = task.title,
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
            )

            Column {
                Text(
                    text = "  " + task.course + ": " + task.title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 30.sp,
                    modifier = Modifier.padding(7.dp),
//                    color = Color(0xFF1D0101)
                )
            }
        }
    }
}

