package com.bcit.frontend.components

import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.bcit.frontend.dataClasses.Task


private class StatefulTask (val task: Task,
                            initState: DragAnchors,
                            val density: Density) {
    @OptIn(ExperimentalFoundationApi::class)
    val dragState = AnchoredDraggableState(
            initialValue = initState,
            positionalThreshold = { distance: Float -> distance * 0.8f },
            velocityThreshold = { with(density) { 100.dp.toPx() } },
            animationSpec = tween()
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskList(
    tasks: SnapshotStateList<Task>
    ) {
    val localDensity = LocalDensity.current

    var statefulTasks: MutableList<StatefulTask> = mutableListOf()
    for (t in tasks){
        statefulTasks.add(StatefulTask(t, DragAnchors.OnScreen, localDensity))
    }

    LaunchedEffect(tasks) {
        val newList = mutableListOf<StatefulTask>()
        for (t in tasks) {
            newList.add(StatefulTask(t, DragAnchors.OnScreen, localDensity))
        }
        statefulTasks = newList
        Log.d("TaskListUpdate", "Updated: $newList")
    }

    LazyColumn {
        items(statefulTasks.size) {
//            TaskCard(statefulTasks[it].dragState)
        }
    }
}
