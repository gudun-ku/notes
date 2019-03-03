package com.beloushkin.android.learn.notes.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beloushkin.android.learn.notes.R
import com.beloushkin.android.learn.notes.models.Note
import com.beloushkin.android.learn.notes.tasks.TaskAdapter
import kotlinx.android.synthetic.main.item_note.view.*

class NoteAdapter(
    private val notesList: MutableList<Note> = mutableListOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder  =
    ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))

    override fun getItemCount() = notesList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as NoteAdapter.ViewHolder).onBind(notesList.get(position))
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(note: Note) {
            view.noteDescriptionView.text = note.description
        }
    }
}