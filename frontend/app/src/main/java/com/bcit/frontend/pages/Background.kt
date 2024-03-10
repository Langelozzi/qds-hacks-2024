package com.bcit.frontend.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.bcit.frontend.R

@Composable
fun BackgroundImage() {
    Image(
        painter = painterResource(R.drawable.taskthunderdomeblurred),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}
