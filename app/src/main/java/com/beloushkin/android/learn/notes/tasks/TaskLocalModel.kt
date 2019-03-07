package com.beloushkin.android.learn.notes.tasks

import com.beloushkin.android.learn.notes.models.Task
import com.beloushkin.android.learn.notes.models.Todo
import javax.inject.Inject

class TaskLocalModel @Inject constructor() : ITaskModel{

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

    override fun addNote(task: Task, callback: successCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateNote(task: Task, callback: successCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteNote(task: Task, callback: successCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun retrieveNotes(): List<Task> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}