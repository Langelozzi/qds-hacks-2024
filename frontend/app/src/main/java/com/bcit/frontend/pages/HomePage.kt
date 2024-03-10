package com.bcit.frontend.pages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bcit.frontend.components.BottomUpDrawerComponent
import com.bcit.frontend.components.TaskList
import com.bcit.frontend.dataClasses.Task
import com.bcit.frontend.enums.NavPages

@Composable
fun HomePage(
    setActivePage: (NavPages) -> Unit,
    tasks: SnapshotStateList<Task>,
    addTask: (task: Task) -> Unit,
    completeTask: (Task) -> Unit
) {
    var drawerIsActive by remember { mutableStateOf(false) }
    BottomUpDrawerComponent(
        taskPageContent = { FormPage(onSubmit = { drawerIsActive = false }, addTask) },
        showBottomSheet = drawerIsActive,
        onShowBottomSheetChange = { drawerIsActive = it },
        onDrawerStateChange = { isActive -> drawerIsActive = isActive },
    )
    AnimatedVisibility(visible = !drawerIsActive) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopStart
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (tasks.isEmpty()) {
                    Card(
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable {
                                setActivePage(NavPages.VS)
                            },
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text("It looks like you haven't sorted any of your tasks yet.")
                            Text(
                                "Click here to start the sorting smackdown!"
                            )
                        }
                    }

                }
            }
            TaskList(tasks = tasks, completeTask)
        }

    }


}
