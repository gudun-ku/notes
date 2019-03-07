package com.beloushkin.android.learn.notes.notes

import android.util.Log
import com.beloushkin.android.learn.notes.models.Note
import javax.inject.Inject

class NoteLocalModel @Inject constructor() : INoteModel{


    override fun getFakeData(): MutableList<Note> = mutableListOf(
        Note("Note description one"),
        Note("Note description two"),
        Note("Note description three"),
        Note("Note description four")
    )

    override fun addNote(note: Note, callback: successCallback) {
        Log.d("Udemy Kotlin course", note.toString())
        callback.invoke(true)
    }

    override fun updateNote(note: Note, callback: successCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteNote(note: Note, callback: successCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun retrieveNotes(): List<Note> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}