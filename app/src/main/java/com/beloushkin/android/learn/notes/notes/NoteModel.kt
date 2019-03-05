package com.beloushkin.android.learn.notes.notes

import com.beloushkin.android.learn.notes.models.Note

class NoteModel {

    fun getFakeData(): MutableList<Note> = mutableListOf(
        Note("Note description one"),
        Note("Note description two"),
        Note("Note description three"),
        Note("Note description four")
    )
}