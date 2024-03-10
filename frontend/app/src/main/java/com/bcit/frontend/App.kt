package com.bcit.frontend

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bcit.frontend.components.NavBar
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
                    VsPage()
                }
                composable(route = "Home") {
                    HomePage()
                }
                composable(route = "Complete") {
                    CompletedPage()
                }
            }
        }
    }
}