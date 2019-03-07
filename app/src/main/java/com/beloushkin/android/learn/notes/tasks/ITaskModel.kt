package com.beloushkin.android.learn.notes.tasks

import com.beloushkin.android.learn.notes.models.Task

typealias successCallback = (Boolean) -> Unit

interface ITaskModel {

    // CRUD OPERATIONS
    fun addTask(task: Task, callback: successCallback)
    fun updateTask(task: Task, callback: successCallback)
    fun deleteTask(task: Task, callback: successCallback)
    fun retrieveTasks(): List<Task>

    fun getFakeData(): MutableList<Task>

}