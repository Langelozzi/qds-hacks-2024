package com.bcit.frontend.dataClasses

data class Task(
    val title: String,
    var course: String,
    val type: TaskType,
    val worth: Double
)

enum class TaskType {
    ASSIGNMENT, LAB, QUIZ, MIDTERM, OTHER
}
