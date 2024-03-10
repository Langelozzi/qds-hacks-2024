package com.bcit.frontend.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bcit.frontend.components.TextFieldWithIcons
import com.bcit.frontend.dataClasses.Task
import com.bcit.frontend.dataClasses.TaskType
import com.bcit.frontend.enums.FormFieldsEnum


@Composable
fun FormPage(onSubmit: () -> Unit) {
    var courseName by rememberSaveable { mutableStateOf("") }
    var taskType by rememberSaveable { mutableStateOf(TaskType.OTHER) }
    var taskWeight by rememberSaveable { mutableStateOf("") }
    var dueDate by rememberSaveable { mutableStateOf("") }
    val formFieldValues = remember { mutableStateMapOf<FormFieldsEnum, String>() }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormFieldsEnum.entries.forEach { formField ->
            TextFieldWithIcons(
                formField = formField,
                text = formFieldValues[formField] ?: "",
                onTextChange = { newValue ->
                    formFieldValues[formField] = newValue
                }
            )
            Spacer(modifier = Modifier.height(8.dp))

        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                val weight = taskWeight.toDoubleOrNull() ?: 0.0
                val task = Task(
                    title = "Static Title", // Placeholder or adjust as needed
                    course = courseName,
                    type = taskType,
                    worth = weight,
                )
                onSubmit()
                // TODO: Handle the Task object (e.g., state update, submission)
            },
            modifier = Modifier.align(Alignment.CenterHorizontally),
        ) {
            Text("Submit")
        }
    }
}