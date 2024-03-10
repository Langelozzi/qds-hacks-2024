package com.bcit.frontend.pages

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bcit.frontend.R
import com.bcit.frontend.enums.NavPages

val customFontFamily = FontFamily(
    Font(R.font.upheavtt, FontWeight.Normal)
)

@Composable
fun StartUpPage(setActivePage:(NavPages) -> Unit) {
    Image(
        painter = painterResource(R.drawable.taskthunderdome),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 0.dp, 0.dp, 288.dp)
            .clickable{
                Log.d("startup", "startup clicked")
                setActivePage(NavPages.VS)},
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            text = "Tap to Begin...",
            style = MaterialTheme.typography.bodyLarge
                .copy(
                    fontFamily = com.bcit.frontend.components.customFontFamily,
                    fontSize = 38.sp,
                    color = Color(0xFFFFFFFF)
                )
        )
    }
}

