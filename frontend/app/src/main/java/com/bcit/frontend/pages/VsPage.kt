package com.bcit.frontend.pages

import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.bcit.frontend.R
import com.bcit.frontend.components.DragAnchors
import com.bcit.frontend.components.TaskCard
import com.bcit.frontend.dataClasses.Task

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VsPage(tasks: SnapshotStateList<Task>, taskSwipped: (task: Task) -> Unit) {
    // Variables
    val density = LocalDensity.current

    @OptIn(ExperimentalFoundationApi::class)
    val draggableCardStates = remember {
        mutableListOf(
            AnchoredDraggableState(
                initialValue = DragAnchors.OnScreen,
                positionalThreshold = { distance: Float -> distance * 0.8f },
                velocityThreshold = { with(density) { 100.dp.toPx() } },
                animationSpec = tween()
            ),
            AnchoredDraggableState(
                initialValue = DragAnchors.OnScreen,
                positionalThreshold = { distance: Float -> distance * 0.8f },
                velocityThreshold = { with(density) { 100.dp.toPx() } },
                animationSpec = tween()
            )
        )
    }


    var buttonClicked by remember { mutableStateOf(false) }
    LaunchedEffect(buttonClicked) {
        if (buttonClicked) {
            for (draggedState in draggableCardStates) {
                draggedState.animateTo(DragAnchors.OnScreen)
            }
            buttonClicked = false
        }
    }

    val onButtonClick = {
        buttonClicked = true
        taskSwipped(tasks[0])
    }
    BackgroundImage()
    Column(verticalArrangement = Arrangement.SpaceAround) {
        TaskCard(draggableCardStates[0], tasks[0], taskSwipped)

        Image(
            painter = painterResource(id = R.drawable.vs),
            contentDescription = "vs", modifier = Modifier
                .fillMaxWidth()
                .size(100.dp)
                .zIndex(1f)
        )
        TaskCard(draggableCardStates[1], tasks[1], taskSwipped)
    }
//
//    Button(onClick = onButtonClick) {
//            Text("Bring back to center")
//        }

}