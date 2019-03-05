package com.beloushkin.android.learn.notes.tasks

interface TaskListViewContract {
    fun onTodoUpdated(taskIndex: Int, todoIndex: Int, isComplete: Boolean)
}