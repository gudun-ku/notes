package com.beloushkin.android.learn.notes.create

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.beloushkin.android.learn.notes.R
import com.beloushkin.android.learn.notes.foundations.ApplicationScope
import com.beloushkin.android.learn.notes.foundations.NullFieldChecker
import com.beloushkin.android.learn.notes.models.Note
import com.beloushkin.android.learn.notes.notes.INoteModel
import com.beloushkin.android.learn.notes.notes.successCallback
import kotlinx.android.synthetic.main.fragment_create_note.*
import toothpick.Toothpick
import javax.inject.Inject

class CreateNoteFragment : Fragment(), NullFieldChecker {

    @Inject
    lateinit var model: INoteModel

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Toothpick.inject(this, ApplicationScope.scope)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_note, container, false)
    }

    fun saveNote(callback: successCallback) {
        createNote()?.let {
            model.addNote(it){
                callback.invoke(true)
            }
        }?:callback.invoke(false)
    }

    fun createNote(): Note? = if (!hasNullField()) {
        Note(description = noteEditText.editableText.toString())
    } else {
        null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction()
    }

    companion object {
        fun newInstance() = CreateNoteFragment()
    }

    override fun hasNullField(): Boolean = noteEditText.editableText.isNullOrEmpty()
}
