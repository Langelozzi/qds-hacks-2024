package com.bcit.frontend.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.bcit.frontend.components.TaskList
import com.bcit.frontend.dataClasses.Task

@Composable
fun CompletedPage(completedTasks: SnapshotStateList<Task>) {
    BackgroundImage()
    Box{
        Text("Completed Page")
        TaskList(completedTasks, {})
    }
}