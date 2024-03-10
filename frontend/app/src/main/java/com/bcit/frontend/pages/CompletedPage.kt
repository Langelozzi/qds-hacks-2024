package com.bcit.frontend.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.bcit.frontend.components.TaskList
import com.bcit.frontend.dataClasses.Task

@Composable
fun CompletedPage(completedTasks: SnapshotStateList<Task>, deleteCompletedTask: (Task) -> Unit) {
    Box{
        TaskList(completedTasks, deleteCompletedTask)
    }
}