package com.bcit.frontend.components

import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.bcit.frontend.dataClasses.Task


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun StatefulTask (task: Task,
                  initState: DragAnchors,
                  density: Density,
                  completeTask: (Task) -> Unit
                  ) {

    var minimized by remember { mutableStateOf(true) }

    val dragState = AnchoredDraggableState(
        initialValue = initState,
        positionalThreshold = { distance: Float -> distance * 0.8f },
        velocityThreshold = { with(density) { 100.dp.toPx() } },
        animationSpec = tween()
    )

    Box(
        modifier = Modifier
            .clickable {
                Log.d("TaskListClick", "item clicked: $task")
                minimized = !minimized
            }
    ) {
        if (minimized) {
            TaskListItem(dragState, task, completeTask)
        } else {
            TaskCard(dragState, task, completeTask)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskList(
    tasks: SnapshotStateList<Task>,
    completeTask: (Task) -> Unit
    ) {

    LazyColumn {
        items(tasks.size) {
            StatefulTask(
                task = tasks[it],
                initState = DragAnchors.OnScreen,
                density = LocalDensity.current,
                completeTask
            )
        }
    }
}
