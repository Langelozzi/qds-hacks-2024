package com.bcit.frontend.enums

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

enum class FormFieldsEnum(val displayName: String, val icon: () -> ImageVector) {
    CourseName("Course Name", { Icons.Filled.Build }),
    Title("Title", { Icons.Filled.Info }),
    TaskType("Task Type", { Icons.Filled.Build }),
    DueDate("Due Date", { Icons.Filled.DateRange }),
    Difficulty("Difficulty", { Icons.Filled.Star }),
    Weight("Task Weight", { Icons.Filled.Edit }),

}