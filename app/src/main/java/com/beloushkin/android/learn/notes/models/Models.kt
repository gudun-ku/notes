package com.beloushkin.android.learn.notes.models

data class Task @JvmOverloads constructor(
    var title: String,
    val todos: MutableList<Todo>? = mutableListOf(),
    var tag: Tag? = null
)

data class Todo(
    var description: String,
    var isComplete: Boolean
)

data class Note(
    var description: String,
    var tag: Tag? = null
)

data class Tag(
    val name: String,
    val colourResId: Int
)
