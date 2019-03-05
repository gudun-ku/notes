package com.beloushkin.android.learn.notes.notes

import androidx.lifecycle.ViewModel
import com.beloushkin.android.learn.notes.models.Note

class NoteViewModel: ViewModel() {

    fun getFakeData(): MutableList<Note> = mutableListOf(
        Note("Note description one"),
        Note("Note description two"),
        Note("Note description three"),
        Note("Note description four"))
}