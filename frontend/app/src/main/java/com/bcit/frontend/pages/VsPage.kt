package com.bcit.frontend.pages

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bcit.frontend.R
import com.bcit.frontend.components.DragAnchors
import com.bcit.frontend.components.TaskCard
import com.bcit.frontend.dataClasses.Task
import com.bcit.frontend.enums.NavPages
import com.bcit.frontend.helpers.TaskSorter

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VsPage(
    setActivePage: (NavPages) -> Unit,
    tasksToSort: SnapshotStateList<Task>,
    addSortedTasks: (Array<Task>) -> Unit,
    onReSortTasks: () -> Unit,
) {
    // Variables
    val density = LocalDensity.current

    // States
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
    val unsortedTasks = remember {
        mutableStateListOf<Task>()
    }
    val sortedTasks = remember {
        mutableStateListOf<Task>()
    }
    val winningTasks = remember {
        mutableStateListOf<Task>()
    }
    val nextTasks = remember {
        mutableStateListOf<Task>()
    }
    var sortingDone by remember { mutableStateOf(tasksToSort.isEmpty()) }
    var currentTaskRank by remember { mutableIntStateOf(1) }

    val resetStates = {
        unsortedTasks.clear()
        sortedTasks.clear()
        winningTasks.clear()
        nextTasks.clear()
        currentTaskRank = 1
    }


    val assignTasksToLists = { winningTask: Task, losingTask: Task ->
        winningTasks.add(winningTask)

        losingTask.rank = currentTaskRank
        sortedTasks.add(losingTask)
    }

    val updateNextTasks: () -> Unit = {
        nextTasks.clear()

        // if there are no unsorted tasks left
        if (unsortedTasks.size <= 1 && winningTasks.size <= 1) {
            // assign rank to all of the remaining tasks
            unsortedTasks.addAll(winningTasks)
            for (unsortedTask in unsortedTasks) {
                unsortedTask.rank = currentTaskRank
                sortedTasks.add(unsortedTask)
            }

            // sort the tasks based on their cumulative value
            val calculatedSortedTasks = TaskSorter().sortTasks(sortedTasks)

            // add the sorted tasks to the sorted list
            addSortedTasks(calculatedSortedTasks)

            resetStates()
            sortingDone = true
        }
        // if there is not enough unsorted to compare but there are winning tasks, so start next round
        else if (unsortedTasks.size <= 1) {
            unsortedTasks.addAll(winningTasks)
            winningTasks.clear()
            currentTaskRank++
        }

        if (unsortedTasks.size > 1) {
            nextTasks.add(unsortedTasks.removeAt(0))
            nextTasks.add(unsortedTasks.removeAt(0))
        }
    }

    val onTaskSwipe: (Task) -> Unit = { task: Task ->
        if (task == nextTasks[0]) {
            assignTasksToLists(nextTasks[1], nextTasks[0])
        } else if (task == nextTasks[1]) {
            assignTasksToLists(nextTasks[0], nextTasks[1])
        }
        updateNextTasks()
    }

    // Effects
    LaunchedEffect(tasksToSort.size) {
        unsortedTasks.clear()
        unsortedTasks.addAll(tasksToSort)
        if (nextTasks.isEmpty()) updateNextTasks()
    }

    LaunchedEffect(sortingDone) {
        if (sortingDone) {
            draggableCardStates[0].animateTo(DragAnchors.OnScreen)
            draggableCardStates[1].animateTo(DragAnchors.OnScreen)
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!sortingDone && nextTasks.size > 1) {
            TaskCard(draggableCardStates[0], nextTasks[0], onTaskSwipe)
            Image(
                modifier = Modifier
                    .size(110.dp)
                    .fillMaxWidth(),
                painter = painterResource(id = R.drawable.vsdarktransparent),
                contentDescription = "test",
            )
            TaskCard(draggableCardStates[1], nextTasks[1], onTaskSwipe)
        }
        if (sortingDone) {
            Card(modifier = Modifier) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    Text(
                        modifier = Modifier.padding(top = 10.dp),
                        text = "TKO! The sorting is done!"
                    )
                    Row(
                        modifier = Modifier.padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            modifier = Modifier.padding(2.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD352AE)),
                            onClick = {
                                onReSortTasks()
                                sortingDone = false
                            }) {
                            Text("Resort")
                        }
                        Button(
                            modifier = Modifier.padding(2.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF198296)),
                            onClick = {
                                setActivePage(NavPages.Home)
                            }) {
                            Text("View Results")
                        }
                    }
                }
            }
        }
    }

}