package com.beloushkin.android.learn.notes.notes

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beloushkin.android.learn.notes.models.Note
import kotlinx.android.synthetic.main.fragment_tasks_list.view.*

class NoteListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1
) : ConstraintLayout(context, attrs, defStyleAttr) {

    lateinit var touchActionDelegate: NotesListFragment.TouchActionDelegate
    lateinit var dataActionDelegate: NoteListViewContract
    lateinit var adapter: NoteAdapter


    fun initView(taDelegate: NotesListFragment.TouchActionDelegate,
                 daDelegate: NoteListViewContract) {
        setDelegates(taDelegate, daDelegate)
        setUpView()
    }

    private fun setDelegates(taDelegate: NotesListFragment.TouchActionDelegate,
                             daDelegate: NoteListViewContract)
    {
        touchActionDelegate = taDelegate
        dataActionDelegate = daDelegate
    }

    private fun setUpView() {
        recyclerView.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
        adapter = NoteAdapter(
            touchActionDelegate = touchActionDelegate,
            dataActionDelegate = dataActionDelegate
        )
        recyclerView.adapter = adapter
    }

    fun updateList(list: List<Note>) {
        adapter.updateList(list)
    }
}