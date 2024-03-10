package com.bcit.frontend

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bcit.frontend.components.NavBar
import com.bcit.frontend.dataClasses.Task
import com.bcit.frontend.dataClasses.TaskType
import com.bcit.frontend.enums.NavPages
import com.bcit.frontend.pages.CompletedPage
import com.bcit.frontend.pages.HomePage
import com.bcit.frontend.pages.VsPage

@Composable
fun App() {
    val navController = rememberNavController()

    var activePage by remember { mutableStateOf(NavPages.Home) }
    LaunchedEffect(activePage) {
        when (activePage) {
            NavPages.Home -> navController.navigate("Home")
            NavPages.VS -> navController.navigate("VS")
            NavPages.Complete -> navController.navigate("Complete")
        }
    }

    val setActivePage = { page: NavPages ->
        activePage = page
    }

    val completedTasks = remember {
        mutableStateListOf(
            Task("Lab 1", "CPSC", TaskType.LAB, 0.05, "2022-10-10", 2, R.drawable.statslab2),
            Task("Lab 1", "Stats", TaskType.LAB, 0.05, "2022-10-10", 2, R.drawable.algolab3),
        )
    }

    val incompleteTasks = remember {
        mutableStateListOf(
            Task("Lab 1", "CPSC", TaskType.LAB, 0.05, "2022-10-10", 2, R.drawable.stats2),
            Task("quiz 1", "Stats", TaskType.LAB, 0.05, "2022-10-10", 2, R.drawable.algolab3),
            Task("midterm 1", "Maths", TaskType.LAB, 0.05, "2022-10-10",2, R.drawable.ooplab2),
            Task("assignement 1", "AIML", TaskType.LAB, 0.05, "2022-10-10",2, R.drawable.ooplab1),
            Task("Lab 1", "CPSC", TaskType.LAB, 0.05, "2022-10-10", 2, R.drawable.statslab2),
            Task("Lab 1", "Stats", TaskType.LAB, 0.05, "2022-10-10", 2, R.drawable.algolab3),
        )
    }
    var displayedTasksIndexes = remember{
        mutableStateListOf(0,1)
    }
    var displayedTasks = remember {
        mutableStateListOf(incompleteTasks[displayedTasksIndexes[0]], incompleteTasks[displayedTasksIndexes[1]])
    }

    val removeTask: (Task) -> Unit = { task ->
        // this function should remove the passed task from the list
    }
    val taskSwipped: (Task) -> Unit = { task ->
        displayedTasksIndexes[0] += 1
        displayedTasksIndexes[1] += 1
        displayedTasks[0] = incompleteTasks[displayedTasksIndexes[0]]
        displayedTasks[1] = incompleteTasks[displayedTasksIndexes[1]]
        // you should be able to see when this is swiped it gets logged
    }
    val addTask: (Task) -> Unit = { task ->
        incompleteTasks.add(task)
        // you should be able to see when this is swiped it gets logged
    }

    Column {
        NavBar(navController, activePage, setActivePage)
        Box(
            modifier = Modifier

        ) {
            NavHost(
                navController = navController,
                startDestination = "home"
            ) {
                composable(route = "VS") {
                    VsPage(displayedTasks, taskSwipped)
                }
                composable(route = "Home") {
                    HomePage(addTask)
                }
                composable(route = "Complete") {
                    CompletedPage()
                }
            }
        }
    }
}