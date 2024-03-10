package com.bcit.frontend.dataClasses

import com.bcit.frontend.R
import java.time.LocalDate

data class Task(
    val title: String = "",
    var course: String = "",
    val type: TaskType = TaskType.ASSIGNMENT,
    val weight: Double = 1.0,
    val dueDate: LocalDate = LocalDate.now().plusDays(5),
    val difficulty: Int = 1,
    val imageId: Int = R.drawable.ooplab2,
    var rank: Int = -1
)

enum class TaskType {
    ASSIGNMENT, LAB, QUIZ, MIDTERM, OTHER
}
