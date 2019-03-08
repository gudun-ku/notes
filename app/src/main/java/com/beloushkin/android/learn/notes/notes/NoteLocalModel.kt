package com.beloushkin.android.learn.notes.notes

import android.util.Log
import com.beloushkin.android.learn.notes.application.NoteApplication
import com.beloushkin.android.learn.notes.database.RoomDatabaseClient
import com.beloushkin.android.learn.notes.models.Note
import kotlinx.coroutines.*
import javax.inject.Inject

const val TIMEOUT_DURATION_MILLIS = 3000L

class NoteLocalModel @Inject constructor() : INoteModel{

    private var databaseClient = RoomDatabaseClient.getInstance(NoteApplication.instance.applicationContext)

    private suspend fun performOperationWithTimeout(function: () -> Unit, callback: successCallback) {
        val job = GlobalScope.async{
            try {
                withTimeout(TIMEOUT_DURATION_MILLIS) {
                    function.invoke()
                }
            } catch (e: java.lang.Exception) {
                callback.invoke(false)
            }
        }
        job.await()
        callback.invoke(true)

    }

    override suspend fun addNote(note: Note, callback: successCallback) {
        Log.d("Notes. Add", note.toString())
        performOperationWithTimeout({databaseClient.noteDAO().addNote(note)},callback)
    }

    override suspend fun updateNote(note: Note, callback: successCallback) {
        Log.d("Notes. Update", note.toString())
        performOperationWithTimeout({databaseClient.noteDAO().updateNote(note)},callback)
    }

    override suspend fun deleteNote(note: Note, callback: successCallback) {
        Log.d("Notes. Delete", note.toString())
        performOperationWithTimeout({databaseClient.noteDAO().deleteNote(note)},callback)
    }

    override suspend fun retrieveNotes(callback: (List<Note>?) -> Unit) {
        Log.d("Notes. Retrieve", "List of notes")
        val job = GlobalScope.async {
            withTimeoutOrNull(TIMEOUT_DURATION_MILLIS) {
                databaseClient.noteDAO().retrieveNotes()
            }
        }
        callback.invoke(job.await())
    }
}