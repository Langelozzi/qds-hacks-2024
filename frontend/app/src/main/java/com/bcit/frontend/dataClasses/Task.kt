package com.bcit.frontend.dataClasses

import com.bcit.frontend.R
import java.time.LocalDate

data class Task(
    val title: String,
    var course: String,
    val type: TaskType,
    val weight: Double,
    val dueDate: LocalDate,
    val difficulty: Int,
    val imageId: Int = R.drawable.ooplab2,
    var rank: Int = -1
)

enum class TaskType {
    ASSIGNMENT, LAB, QUIZ, MIDTERM, OTHER
}
