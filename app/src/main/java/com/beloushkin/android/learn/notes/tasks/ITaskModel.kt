package com.beloushkin.android.learn.notes.tasks

import com.beloushkin.android.learn.notes.models.Task
import com.beloushkin.android.learn.notes.models.Todo

typealias successCallback = (Boolean) -> Unit

interface ITaskModel {

    // CRUD OPERATIONS
    suspend fun addTask(task: Task, callback: successCallback)
    suspend fun updateTask(task: Task, callback: successCallback)
    suspend fun updateTodo(todo: Todo, callback: successCallback)
    suspend fun deleteTask(task: Task, callback: successCallback)
    fun retrieveTasks(callback: (List<Task>?) -> Unit)

}