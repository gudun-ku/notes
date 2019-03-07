package com.beloushkin.android.learn.notes.notes

import com.beloushkin.android.learn.notes.models.Note

interface NoteListViewContract {
    fun onDeleteNote(note: Note)
}