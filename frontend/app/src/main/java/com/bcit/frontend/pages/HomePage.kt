package com.bcit.frontend.pages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bcit.frontend.components.BottomUpDrawerComponent

@Composable
fun HomePage() {
    var drawerIsActive by remember { mutableStateOf(false) } // This state now directly controls the bottom drawer's visibility

    BottomUpDrawerComponent(
        taskPageContent = { FormPage(onSubmit = { drawerIsActive = false }) }, // Pass onSubmit to close the drawer
        showBottomSheet = drawerIsActive, // Pass the state controlling visibility
        onShowBottomSheetChange = { drawerIsActive = it }, // Function to change visibility
        onDrawerStateChange = { isActive -> drawerIsActive = isActive } // This might be optional now, depending on your needs
    )
    AnimatedVisibility(visible = !drawerIsActive) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            // Your main content that should be hidden/shown when the drawer is toggled
            Text(text = "Welcome to the HomePage!")
        }
    }
}
