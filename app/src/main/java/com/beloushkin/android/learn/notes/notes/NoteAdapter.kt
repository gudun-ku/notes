package com.beloushkin.android.learn.notes.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beloushkin.android.learn.notes.R
import com.beloushkin.android.learn.notes.foundations.BaseRecyclerAdapter
import com.beloushkin.android.learn.notes.models.Note
import kotlinx.android.synthetic.main.item_note.view.*

class NoteAdapter(
    notesList: MutableList<Note> = mutableListOf()
) : BaseRecyclerAdapter<Note>(notesList) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder  =
    ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))

    class ViewHolder(view: View) : BaseViewHolder<Note>(view) {
        override fun onBind(data: Note) {
            view.descriptionView.text = data.description
        }
    }
}