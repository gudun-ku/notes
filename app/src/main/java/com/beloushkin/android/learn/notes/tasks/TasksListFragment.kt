package com.beloushkin.android.learn.notes.tasks


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.beloushkin.android.learn.notes.R
import com.beloushkin.android.learn.notes.models.Task
import com.beloushkin.android.learn.notes.models.Todo
import kotlinx.android.synthetic.main.fragment_tasks_list.*

class TasksListFragment : Fragment() {

    lateinit var touchActionDelegate: TouchActionDelegate

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tasks_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
        val adapter = TaskAdapter(mutableListOf(
            Task("Testing one", mutableListOf(
                Todo("Todo test one", true),
                Todo("Todo test two")
            )),
            Task("Testing two"),
            Task("Testing three", mutableListOf(
                Todo("Todo test three", true),
                Todo("Todo test four"),
                Todo("Todo test five")
            ))
        ),touchActionDelegate)
        recyclerView.adapter = adapter

    }

    companion object {
        fun newInstance() = TasksListFragment()
    }

}
