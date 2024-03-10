package com.bcit.frontend.dataClassees

enum class TaskType {
    ASSIGNMENT, LAB, QUIZ, MIDTERM, OTHER
}
data class Task(
    val title: String,
    val course: String,
    val type: TaskType,
    val weight: Double,
    val dueDate: String,
    val difficulty: Int,
)

