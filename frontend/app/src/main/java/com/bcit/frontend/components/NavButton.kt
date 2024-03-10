package com.bcit.frontend.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NavButton(
    text: String = "",
    icon: @Composable () -> Unit = {},
    isActive: Boolean,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    val buttonColor =
        if (isActive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
    val circleColor =
        if (isActive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background

    IconButton(
        modifier = Modifier
            .background(buttonColor, shape = CircleShape)
            .size(30.dp),
        onClick = onClick,
    ) {
        Box(
            modifier = Modifier
                .background(circleColor, shape = CircleShape)
        ) {
            if (text.isNotEmpty()) {
                Text(text, fontSize = 12.sp)
            } else {
                icon()
            }
        }
        content()
    }
}
