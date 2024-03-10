package com.bcit.frontend.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bcit.frontend.enums.NavPages

@Composable
fun NavBar(
    navController: NavController,
    activePage: NavPages,
    setActivePage: (NavPages) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .shadow(60.dp, shape = RoundedCornerShape(30.dp))
                .padding(2.dp) // Adjust padding as needed
        ) {
            Row(
                modifier = Modifier
                    .background(Color(0x75C7C6C6), shape = RoundedCornerShape(30.dp))
                    .padding(1.dp),
                horizontalArrangement = Arrangement.Center,

                ) {
                NavButton(
                    icon = {
                        Icon(
                            Icons.Rounded.Home,
                            contentDescription = "",
                        )
                    },
                    isActive = activePage == NavPages.Home,
                    onClick = {
                        setActivePage(NavPages.Home)
                    })
                NavButton(
                    text = "VS.",
                    isActive = activePage == NavPages.VS,
                    onClick = {
                        setActivePage(NavPages.VS)
                    })
                NavButton(
                    icon = {
                        Icon(Icons.Rounded.Check, contentDescription = "")
                    },
                    isActive = activePage == NavPages.Complete,
                    onClick = {
                        setActivePage(NavPages.Complete)
                    })


            }
        }
    }

}