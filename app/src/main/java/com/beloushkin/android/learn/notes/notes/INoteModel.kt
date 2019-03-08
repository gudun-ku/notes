package com.beloushkin.android.learn.notes.notes

import com.beloushkin.android.learn.notes.models.Note

typealias successCallback = (Boolean) -> Unit

interface INoteModel {

    // CRUD OPERATIONS
    suspend fun addNote(note: Note, callback: successCallback)
    suspend fun updateNote(note: Note, callback: successCallback)
    suspend fun deleteNote(note: Note, callback: successCallback)
    suspend fun retrieveNotes(callback: (List<Note>?) -> Unit)
}