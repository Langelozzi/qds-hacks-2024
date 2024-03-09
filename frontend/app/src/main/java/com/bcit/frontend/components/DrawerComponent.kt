package com.bcit.frontend.components
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
@Composable
fun DrawerComponent(
    modifier: Modifier = Modifier,
    taskPageContent: @Composable () -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        modifier = modifier,
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            taskPageContent()
            Button(
                onClick = { coroutineScope.launch { drawerState.close() } },
                content = { Text("Close Drawer") }
            )
        },
        content = {
            Button(
                onClick = { coroutineScope.launch { drawerState.open() } },
                content = { Text("Open Task Page") }
            )
        }
    )
}