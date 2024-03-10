package com.bcit.frontend.pages

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.bcit.frontend.classes.TaskViewModel
import com.bcit.frontend.components.DragAnchors
import com.bcit.frontend.components.TaskCard

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VsPage(taskViewModel: TaskViewModel) {
    // Variables
    val density = LocalDensity.current
    val currentPair by remember(taskViewModel.displayedTasks) {
        mutableStateOf(taskViewModel.displayedTasks[0])
    }
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
            ))
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
        buttonClicked = false
    }


    Column {
            TaskCard(draggableCardStates[0], onClick = {buttonClicked = true},
                task = currentPair?.first!!
            )
            TaskCard(draggableCardStates[1], onClick = {buttonClicked = true},
                task = currentPair?.second!!
            )
        }

    Button(onClick = onButtonClick) {
            Text("Bring back to center")
        }

}