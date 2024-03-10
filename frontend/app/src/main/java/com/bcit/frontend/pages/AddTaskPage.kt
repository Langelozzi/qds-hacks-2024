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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bcit.frontend.components.TextFieldWithIcons
import com.bcit.frontend.dataClassees.Task
import com.bcit.frontend.dataClassees.TaskType
import com.bcit.frontend.enums.FormFieldsEnum


@Composable
fun FormPage(onSubmit: (Task) -> Unit) {
    val formFieldValues = remember { mutableStateMapOf<FormFieldsEnum, String>() }
    FormFieldsEnum.entries.forEach { formField ->
        if (!formFieldValues.containsKey(formField)) {
            formFieldValues[formField] = ""
        }
    }

    var taskType by rememberSaveable { mutableStateOf(TaskType.OTHER) }
    var difficulty by rememberSaveable { mutableIntStateOf(1) } // Default difficulty

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
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                val title = formFieldValues[FormFieldsEnum.Title] ?: ""
                val courseName = formFieldValues[FormFieldsEnum.CourseName] ?: ""
                val taskWeight = formFieldValues[FormFieldsEnum.Weight]?.toDoubleOrNull() ?: 0.0
                val dueDate = formFieldValues[FormFieldsEnum.DueDate] ?: ""

                val task = Task(
                    title = title,
                    course = courseName,
                    type = taskType,
                    weight = taskWeight,
                    dueDate = dueDate,
                    difficulty = difficulty
                )
                onSubmit(task)
            },
            modifier = Modifier.align(Alignment.CenterHorizontally),
        ) {
            Text("Submit")
        }
    }
}


