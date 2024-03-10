package com.bcit.frontend.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.bcit.frontend.enums.FormFieldsEnum

@Composable
fun TextFieldWithIcons(
    formField: FormFieldsEnum,
    modifier: Modifier= Modifier
) {
    var text by rememberSaveable { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { text = it },
        placeholder = { Text(formField.displayName) },
        leadingIcon = { Icon(formField.icon(), contentDescription = "${formField.displayName} icon") },
        trailingIcon = { Icon(Icons.Filled.Info, contentDescription = "Info for ${formField.displayName}") },
        modifier = modifier.fillMaxWidth()
    )
}
