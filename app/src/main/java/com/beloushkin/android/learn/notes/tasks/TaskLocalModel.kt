package com.beloushkin.android.learn.notes.tasks

import android.util.Log
import com.beloushkin.android.learn.notes.application.NoteApplication
import com.beloushkin.android.learn.notes.database.RoomDatabaseClient
import com.beloushkin.android.learn.notes.models.Task
import com.beloushkin.android.learn.notes.models.Todo
import javax.inject.Inject

class TaskLocalModel @Inject constructor() : ITaskModel{

    private var databaseClient = RoomDatabaseClient.getInstance(NoteApplication.instance.applicationContext)

    override fun getFakeData(): MutableList<Task> = retrieveTasks().toMutableList()

    override fun addTask(task: Task, callback: successCallback) {
        Log.d("Notes. Add", task.toString())
        databaseClient.taskDAO().addTask(task)
        addTodosInTask(task)
        callback.invoke(true)
    }

    override fun updateTask(task: Task, callback: successCallback) {
        Log.d("Notes. Update", task.toString())
        databaseClient.taskDAO().updateTask(task)
        callback.invoke(true)
    }

    override fun updateTodo(todo: Todo, callback: successCallback) {
        Log.d("Notes. Update", todo.toString())
        databaseClient.taskDAO().updateTodo(todo)
        callback.invoke(true)
    }

    override fun deleteTask(task: Task, callback: successCallback) {
        Log.d("Notes. Delete", task.toString())
        databaseClient.taskDAO().deleteTask(task)
        callback.invoke(true)
    }

    private fun addTodosInTask(task: Task) {
        task.todos?.forEach {todo ->
            databaseClient.taskDAO().addTodo(todo)
        }
    }

    override fun retrieveTasks(): List<Task> = databaseClient.taskDAO().retrieveTasks()

}