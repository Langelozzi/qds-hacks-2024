package com.bcit.frontend.classes

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.bcit.frontend.dataClasses.Task
import com.bcit.frontend.dataClasses.TaskType


class TaskViewModel : ViewModel() {
    var incompleteTasks = mutableStateListOf(
        Task("Lab 1", "Course A", TaskType.LAB, 0.1, "2023-03-15", 1),
        Task( "Quiz 1", "Course B", TaskType.QUIZ, 0.2, "2023-03-16", 2),
        Task( "Assignment 1", "Course C", TaskType.ASSIGNMENT, 0.3, "2023-03-17", 3),
        Task( "Midterm 1", "Course D", TaskType.MIDTERM, 0.4, "2023-03-18", 4)
    )
    private var nextRoundTasks = mutableStateListOf<Task>()
    private var comparisonQueue = ArrayDeque<List<Task>>()
    val displayedTasks = mutableStateListOf<Pair<Task?, Task?>?>(null)


    init {
        prepareInitialComparisons()
    }

    private fun prepareInitialComparisons() {
        comparisonQueue.clear()
        val initialComparisons = incompleteTasks.chunked(2)
        comparisonQueue.addAll(initialComparisons)
        loadNextTasks()
    }

    fun loadNextTasks() {
        if (comparisonQueue.isNotEmpty()) {
            displayedTasks[0] = comparisonQueue.removeFirst().let { it[0] to it.getOrNull(1) }
        } else if (nextRoundTasks.isNotEmpty()) {
            val nextRoundComparisons = nextRoundTasks.chunked(2)
            nextRoundTasks.clear()
            comparisonQueue.addAll(nextRoundComparisons)
            loadNextTasks()
        } else {
            displayedTasks[0] = null
        }
    }


    fun handleComparisonResult(winnerTask: Task, loserTask: Task?) {
        nextRoundTasks.add(winnerTask)
        loserTask?.let { nextRoundTasks.add(it) }
        loadNextTasks()
    }

}


