package com.beloushkin.android.learn.notes.tasks

import com.beloushkin.android.learn.notes.models.Task

typealias successCallback = (Boolean) -> Unit

interface ITaskModel {

    // CRUD OPERATIONS
    fun addNote(task: Task, callback: successCallback)
    fun updateNote(task: Task, callback: successCallback)
    fun deleteNote(task: Task, callback: successCallback)
    fun retrieveNotes(): List<Task>

    fun getFakeData(): MutableList<Task>

}