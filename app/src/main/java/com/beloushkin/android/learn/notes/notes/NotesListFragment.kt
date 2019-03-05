package com.beloushkin.android.learn.notes.notes


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.beloushkin.android.learn.notes.R
import kotlinx.android.synthetic.main.fragment_tasks_list.*


class NotesListFragment : Fragment() {

    lateinit var touchActionDelegate: TouchActionDelegate
    lateinit var viewModel: NoteViewModel
    lateinit var adapter: NoteAdapter

    interface TouchActionDelegate {
        fun onAddButtonClicked(value: String)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        context?.let{
            if (it is TouchActionDelegate) {
                touchActionDelegate = it
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = NoteAdapter(
            touchActionDelegate =  touchActionDelegate
        )
        recyclerView.adapter = adapter
        bindViewModel()
    }

    private fun bindViewModel(){
        viewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        viewModel.noteListLiveData.observe(this, Observer {noteList ->
            adapter.updateList(noteList)
        })
    }

    companion object {
        fun newInstance() = NotesListFragment()
    }

}// Required empty public constructor
