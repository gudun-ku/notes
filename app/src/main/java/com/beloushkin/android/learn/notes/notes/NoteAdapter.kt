package com.beloushkin.android.learn.notes.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beloushkin.android.learn.notes.R
import com.beloushkin.android.learn.notes.foundations.BaseRecyclerAdapter
import com.beloushkin.android.learn.notes.models.Note
import com.beloushkin.android.learn.notes.views.NoteView
import kotlinx.android.synthetic.main.view_add_button.view.*

class NoteAdapter(
    notesList: MutableList<Note> = mutableListOf()
) : BaseRecyclerAdapter<Note>(notesList) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder  = if (viewType == TYPE_ADD_BUTTON) {
        AddButtonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_add_button, parent, false))
    } else {
        NoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))
    }

    class NoteViewHolder(view: View) : BaseViewHolder<Note>(view) {
        override fun onBind(data: Note) {
            (view as NoteView).initView(data)
        }
    }

    class AddButtonViewHolder(view: View ) : BaseRecyclerAdapter.AddButtonViewHolder(view) {
        override fun onBind(data: Unit) {
            view.buttonText.text = view.context.getString(R.string.add_button_task)
        }
    }
}