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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bcit.frontend.components.TextFieldWithIcons
import com.bcit.frontend.dataClasses.Task
import com.bcit.frontend.dataClasses.TaskType
import com.bcit.frontend.enums.FormFieldsEnum
import java.time.LocalDate


@Composable
fun FormPage(onSubmit: (Task) -> Unit, addTask: (task: Task) -> Unit) {
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
                val dueDateStr = formFieldValues[FormFieldsEnum.DueDate] ?: ""
                var dueDateDate: LocalDate = LocalDate.now().plusDays(10)
                    try {
                        dueDateDate = LocalDate.parse(dueDateStr)
                    } catch (e: Exception) {
                    }

                val task = Task(
                    title = title,
                    course = courseName,
                    type = taskType,
                    weight = taskWeight,
                    dueDate = dueDateDate,
                    difficulty = difficulty
                )
                addTask(task)
                onSubmit(task)
            },
            modifier = Modifier.align(Alignment.CenterHorizontally),
        ) {
            Text("Submit")
        }
    }
}


