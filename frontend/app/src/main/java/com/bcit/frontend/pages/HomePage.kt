package com.bcit.frontend.pages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.bcit.frontend.components.BottomUpDrawerComponent
import com.bcit.frontend.components.TaskList
import com.bcit.frontend.dataClasses.Task

@Composable
fun HomePage(
    tasks: SnapshotStateList<Task>,
    addTask: (task: Task) -> Unit,
    completeTask: (Task) -> Unit
) {
    var drawerIsActive by remember { mutableStateOf(false) }
    BottomUpDrawerComponent(
        taskPageContent = { FormPage(onSubmit = { drawerIsActive = false }, addTask) },
        showBottomSheet = drawerIsActive,
        onShowBottomSheetChange = { drawerIsActive = it },
        onDrawerStateChange = { isActive -> drawerIsActive = isActive }
    )
    AnimatedVisibility(
        visible = !drawerIsActive,
        modifier = Modifier.background(color = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Transparent),
            contentAlignment = Alignment.TopStart
        ) {
            Column(
                modifier = Modifier
                    .background(color = Color.Transparent)
            ) {
                TaskList(tasks = tasks, completeTask)
            }

        }
    }

}
