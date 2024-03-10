package com.bcit.frontend.dataClasses

import com.bcit.frontend.R

data class Task(
    val title: String,
    var course: String,
    val type: TaskType,
    val weight: Double,
    val dueDate: String,
    val difficulty: Int,
    val imageId: Int = R.drawable.ooplab2
)

enum class TaskType {
    ASSIGNMENT, LAB, QUIZ, MIDTERM, OTHER
}
