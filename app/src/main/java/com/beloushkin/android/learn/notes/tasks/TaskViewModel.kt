package com.beloushkin.android.learn.notes.tasks

import androidx.lifecycle.ViewModel
import com.beloushkin.android.learn.notes.models.Task
import com.beloushkin.android.learn.notes.models.Todo

class TaskViewModel : ViewModel(

) {

    fun getFakeData(): MutableList<Task> = mutableListOf(
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
}