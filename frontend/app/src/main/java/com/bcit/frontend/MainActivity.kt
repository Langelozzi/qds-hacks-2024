package com.bcit.frontend

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.bcit.frontend.components.DragAnchors
import com.bcit.frontend.components.SwipeableCard
import com.bcit.frontend.dataClasses.Task
import com.bcit.frontend.dataClasses.TaskType
import com.bcit.frontend.pages.VsPage

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Main()
        }
    }
}
@Composable
fun Main() {
    val tasks = remember {
        mutableStateListOf(
            Task("Lab 6", "Stats", TaskType.LAB, 0.08),
            Task("Lab 7", "Maths", TaskType.LAB, 0.10)
        )
    }
    val addTask: (Task) -> Unit = { task ->
        Log.d("Test" , task.course)
        // you should be able to see when this is swipped it gets logged
    }
    VsPage(tasks, addTask)
}

