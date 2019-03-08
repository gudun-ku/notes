package com.beloushkin.android.learn.notes.notes

import com.beloushkin.android.learn.notes.models.Note

typealias successCallback = (Boolean) -> Unit

interface INoteModel {

    // CRUD OPERATIONS
    fun addNote(note: Note, callback: successCallback)
    fun updateNote(note: Note, callback: successCallback)
    fun deleteNote(note: Note, callback: successCallback)
    fun retrieveNotes(callback: (List<Note>?) -> Unit)
}