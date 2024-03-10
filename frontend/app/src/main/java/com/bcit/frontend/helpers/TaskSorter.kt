package com.bcit.frontend.helpers

import com.bcit.frontend.dataClasses.Task
import java.time.LocalDate

class TaskSorter {
    fun sortTasks(tasks: List<Task>): Array<Task> {
        // Sorting tasks by their calculated value in descending order
        return (tasks.sortedByDescending { getTaskValue(it) }.toTypedArray()).reversedArray()
    }

    private fun getTaskValue(task: Task): Double {
        // find the difference between the due date and today
        val daysUntilDue = task.dueDate.toEpochDay() - LocalDate.now().toEpochDay()

        return (task.weight * task.difficulty * task.rank) / daysUntilDue
    }
}