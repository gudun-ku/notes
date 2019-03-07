package com.beloushkin.android.learn.notes.notes

import android.util.Log
import com.beloushkin.android.learn.notes.application.NoteApplication
import com.beloushkin.android.learn.notes.database.RoomDatabaseClient
import com.beloushkin.android.learn.notes.models.Note
import javax.inject.Inject

class NoteLocalModel @Inject constructor() : INoteModel{

    private var databaseClient = RoomDatabaseClient.getInstance(NoteApplication.instance.applicationContext)

    override fun getFakeData(): MutableList<Note> = retrieveNotes().toMutableList()

//        = mutableListOf(
//        Note(description = "Note description one"),
//        Note(description = "Note description two"),
//        Note(description = "Note description three"),
//        Note(description = "Note description four")
//    )

    override fun addNote(note: Note, callback: successCallback) {
        Log.d("Notes. Add", note.toString())
        databaseClient.noteDAO().addNote(note);
        callback.invoke(true)
    }

    override fun updateNote(note: Note, callback: successCallback) {
        Log.d("Notes. Update", note.toString())
        databaseClient.noteDAO().updateNote(note);
        callback.invoke(true)
    }

    override fun deleteNote(note: Note, callback: successCallback) {
        Log.d("Notes. Delete", note.toString())
        databaseClient.noteDAO().deleteNote(note);
        callback.invoke(true)
    }

    override fun retrieveNotes(): List<Note> = databaseClient.noteDAO().retrieveNotes()
}