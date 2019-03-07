package com.beloushkin.android.learn.notes.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beloushkin.android.learn.notes.models.Note
import toothpick.Toothpick
import javax.inject.Inject

class NoteViewModel: ViewModel(), NoteListViewContract {

    @Inject
    lateinit var model: NoteModel

    private val _noteListLiveData: MutableLiveData<MutableList<Note>> = MutableLiveData()
    val noteListLiveData: LiveData<MutableList<Note>> = _noteListLiveData

    init {
        val scope = Toothpick.openScope(this)
        Toothpick.inject(this, scope)
        _noteListLiveData.postValue(model.getFakeData())
    }
}