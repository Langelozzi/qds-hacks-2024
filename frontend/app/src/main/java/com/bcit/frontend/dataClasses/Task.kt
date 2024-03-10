package com.bcit.frontend.dataClasses

data class Task(
    val title: String,
    var course: String,
    val type: TaskType,
    val weight: Double,
    val dueDate: String,
    val difficulty: Int,
)

enum class TaskType {
    ASSIGNMENT, LAB, QUIZ, MIDTERM, OTHER
}
