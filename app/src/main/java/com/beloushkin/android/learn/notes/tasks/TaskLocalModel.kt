package com.beloushkin.android.learn.notes.tasks

import android.util.Log
import com.beloushkin.android.learn.notes.application.NoteApplication
import com.beloushkin.android.learn.notes.database.RoomDatabaseClient
import com.beloushkin.android.learn.notes.models.Task
import com.beloushkin.android.learn.notes.models.Todo
import javax.inject.Inject

class TaskLocalModel @Inject constructor() : ITaskModel{

    private var databaseClient = RoomDatabaseClient.getInstance(NoteApplication.instance.applicationContext)

    override fun getFakeData(): MutableList<Task> = mutableListOf(
        Task("Testing one", mutableListOf(
            Todo("Todo test one", true),
            Todo("Todo test two")
        )),
        Task("Testing two"),
        Task("Testing three", mutableListOf(
            Todo("Todo test three", true),
            Todo("Todo test four"),
            Todo("Todo test five"),
            Todo("Todo test six")
        ))
    )

    override fun addTask(task: Task, callback: successCallback) {
        Log.d("Udemy Kotlin course", task.toString())
        callback.invoke(true)
    }

    override fun updateTask(task: Task, callback: successCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteTask(task: Task, callback: successCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun retrieveTasks(): List<Task> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}