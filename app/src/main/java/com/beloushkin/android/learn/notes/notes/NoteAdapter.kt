package com.beloushkin.android.learn.notes.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beloushkin.android.learn.notes.R
import com.beloushkin.android.learn.notes.foundations.BaseRecyclerAdapter
import com.beloushkin.android.learn.notes.models.Note
import com.beloushkin.android.learn.notes.navigation.NavigationActivity
import com.beloushkin.android.learn.notes.views.NoteView
import kotlinx.android.synthetic.main.view_add_button.view.*

class NoteAdapter(
    notesList: MutableList<Note> = mutableListOf(),
    val touchActionDelegate: NotesListFragment.TouchActionDelegate,
    val dataActionDelegate: NoteListViewContract
) : BaseRecyclerAdapter<Note>(notesList) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder  =
        NoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))

    inner class NoteViewHolder(view: View) : BaseViewHolder<Note>(view) {
        override fun onBind(data: Note, listIndex: Int) {
            (view as NoteView).initView(data) {
                dataActionDelegate.onDeleteNote(masterList[listIndex])
            }
        }
    }
}