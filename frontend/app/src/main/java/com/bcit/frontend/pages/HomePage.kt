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
    var drawerIsActive by remember { mutableStateOf(false) }

    BottomUpDrawerComponent(taskPageContent = { FormPage() }, onDrawerStateChange = {
        drawerIsActive = it
    })

    AnimatedVisibility(visible = !drawerIsActive) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Welcome to the HomePage!")
        }
    }
}
