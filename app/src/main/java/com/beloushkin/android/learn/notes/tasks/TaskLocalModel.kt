package com.beloushkin.android.learn.notes.tasks

import android.util.Log
import com.beloushkin.android.learn.notes.application.NoteApplication
import com.beloushkin.android.learn.notes.database.RoomDatabaseClient
import com.beloushkin.android.learn.notes.models.Task
import com.beloushkin.android.learn.notes.models.Todo
import kotlinx.coroutines.*

import javax.inject.Inject

const val TIMEOUT_DURATION_MILLIS = 3000L

class TaskLocalModel @Inject constructor() : ITaskModel{

    private var databaseClient = RoomDatabaseClient.getInstance(NoteApplication.instance.applicationContext)

    private suspend fun performOperationWithTimeout(function: () -> Unit, callback: successCallback) {
        val job = GlobalScope.async{
            try {
                withTimeout(TIMEOUT_DURATION_MILLIS) {
                    function.invoke()
                }
            } catch (e: java.lang.Exception) {
                callback.invoke(false)
            }
        }
        job.await()
        callback.invoke(true)
    }

    override suspend fun addTask(task: Task, callback: successCallback) {
        Log.d("Notes. Add", task.toString())
        val masterJob = GlobalScope.async {
            // adds task entity
            try {
                databaseClient.taskDAO().addTask(task)
            } catch (e: Exception) {
                callback.invoke(false)
            }
            // adds todos list component
            val jobTodos = addTodosJob(task)
        }
        masterJob.await()
        callback.invoke(true)
    }

    override suspend fun updateTask(task: Task, callback: successCallback) {
        Log.d("Notes. Update", task.toString())
        performOperationWithTimeout({ databaseClient.taskDAO().updateTask(task)},callback)
        callback.invoke(true)
    }

    override suspend fun updateTodo(todo: Todo, callback: successCallback) {
        Log.d("Notes. Update", todo.toString())
        performOperationWithTimeout({ databaseClient.taskDAO().updateTodo(todo)},callback)
        callback.invoke(true)
    }

    override suspend fun deleteTask(task: Task, callback: successCallback) {
        Log.d("Notes. Delete", task.toString())
        performOperationWithTimeout({ databaseClient.taskDAO().deleteTask(task)},callback)
        callback.invoke(true)
    }

    private fun addTodosJob(task: Task): Job = GlobalScope.async {
        task.todos?.forEach {todo ->
            databaseClient.taskDAO().addTodo(todo)
        }
    }

    override fun retrieveTasks(callback:(List<Task>?) -> Unit) {
        GlobalScope.launch {
            Log.d("Notes. Retrieve", "List of notes")
            val job = GlobalScope.async {
                withTimeoutOrNull(TIMEOUT_DURATION_MILLIS) {
                    databaseClient.taskDAO().retrieveTasks()
                }
            }
            callback.invoke(job.await())
        }
    }

}