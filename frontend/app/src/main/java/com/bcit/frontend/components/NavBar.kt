package com.bcit.frontend.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

enum class NavPages {
    Home,
    VS,
    Complete,
}

@Composable
fun NavBar() {
    var activePage by remember { mutableStateOf(NavPages.Home) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        // rounded border
        Row(
            modifier = Modifier
                .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(30.dp))
                .padding(1.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            NavButton(
                text = "VS.",
                isActive = activePage == NavPages.VS,
                onClick = {
                    activePage = NavPages.VS
                })
            NavButton(
                icon = {
                    Icon(Icons.Rounded.Home, contentDescription = "Home")
                },
                isActive = activePage == NavPages.Home,
                onClick = {
                    activePage = NavPages.Home
                })
            NavButton(
                icon = {
                    Icon(Icons.Rounded.Check, contentDescription = "Home")
                },
                isActive = activePage == NavPages.Complete,
                onClick = {
                    activePage = NavPages.Complete
                })
        }
    }

}