package com.beloushkin.android.learn.notes.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beloushkin.android.learn.notes.foundations.ApplicationScope
import com.beloushkin.android.learn.notes.models.Note
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import toothpick.Toothpick
import javax.inject.Inject

class NoteViewModel: ViewModel(), NoteListViewContract {

    @Inject
    lateinit var model: INoteModel

    private val _noteListLiveData: MutableLiveData<MutableList<Note>> = MutableLiveData()
    val noteListLiveData: LiveData<MutableList<Note>> = _noteListLiveData

    init {
        Toothpick.inject(this, ApplicationScope.scope)
        loadData()
    }

    fun loadData() {
        GlobalScope.launch {
            model.retrieveNotes {nullableList ->
                nullableList?.let {
                    _noteListLiveData.postValue(it.toMutableList())
                }
            }
        }
    }

    override fun onDeleteNote(note: Note) {
        GlobalScope.launch {
            model.deleteNote(note) {
                if (it) {
                    loadData()
                }
            }
        }
    }
}