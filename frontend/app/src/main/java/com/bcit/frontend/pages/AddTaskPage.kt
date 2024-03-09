package com.bcit.frontend.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bcit.frontend.components.TextFieldWithIcons
import com.bcit.frontend.enums.FormFieldsEnum

@Composable
fun FormPage() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormFieldsEnum.entries.forEach { formField ->
            // Pass the Modifier.fillMaxWidth() to make each TextFieldWithIcons fill the full width
            TextFieldWithIcons(formField = formField, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp)) // Add space between each TextFieldWithIcons for better spacing
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                // Implement form submission logic here
            },
            modifier = Modifier.align(Alignment.CenterHorizontally) // Center the button within its parent Column
        ) {
            Text("Submit")
        }
    }
}
