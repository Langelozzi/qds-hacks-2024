package com.bcit.frontend.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NavButton(
    text: String = "",
    icon: @Composable () -> Unit = {},
    isActive: Boolean,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    val buttonColor = if (isActive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
    val circleColor = if (isActive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background

    IconButton(
        modifier = Modifier
            .background(buttonColor, shape = CircleShape),
        onClick = onClick,
    ) {
        Box(
            modifier = Modifier
                .background(circleColor, shape = CircleShape)
        ) {
            if (text.isNotEmpty()) {
                Text(text)
            } else {
                icon()
            }
        }
        content()
    }
}
