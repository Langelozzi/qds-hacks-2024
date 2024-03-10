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
import com.bcit.frontend.components.BottomRightDrawerComponent
import com.bcit.frontend.components.BottomUpDrawerComponent
import com.bcit.frontend.components.DragAnchors
import com.bcit.frontend.components.SwipeableCard
import com.bcit.frontend.components.TextFieldWithIcons
import com.bcit.frontend.pages.FormPage
import com.bcit.frontend.pages.VsPage

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            VsPage()
//            TextFieldWithIcons()
//            FormPage()
//        DrawerComponent(taskPageContent = { FormPage() })
//            BottomRightDrawerComponent(taskPageContent = { FormPage() })
//    BottomUpDrawerComponent(taskPageContent = { FormPage() })

        }
    }
}

