package com.beloushkin.android.learn.notes.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beloushkin.android.learn.notes.models.Note

class NoteViewModel: ViewModel(), NoteListViewContract {

    private val _noteListLiveData: MutableLiveData<MutableList<Note>> = MutableLiveData()
    val noteListLiveData: LiveData<MutableList<Note>> = _noteListLiveData

    init {
        _noteListLiveData.postValue(getFakeData())
    }
    fun getFakeData(): MutableList<Note> = mutableListOf(
        Note("Note description one"),
        Note("Note description two"),
        Note("Note description three"),
        Note("Note description four"))
}