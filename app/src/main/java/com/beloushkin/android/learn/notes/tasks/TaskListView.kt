package com.beloushkin.android.learn.notes.tasks

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beloushkin.android.learn.notes.models.Task
import com.beloushkin.android.learn.notes.navigation.NavigationActivity
import kotlinx.android.synthetic.main.fragment_tasks_list.view.*

class TaskListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1
) : ConstraintLayout(context, attrs, defStyleAttr) {

    lateinit var adapter: TaskAdapter
    private lateinit var touchActionDelegate: TasksListFragment.TouchActionDelegate
    private lateinit var dataActionDelegate: TaskListViewContract

    fun initView(taDelegate: TasksListFragment.TouchActionDelegate,
                 daDelegage: TaskListViewContract) {
        setDelegates(taDelegate, daDelegage)
        setUpView()
    }

    private fun setDelegates(taDelegate: TasksListFragment.TouchActionDelegate,
                             daDelegage: TaskListViewContract) {
        touchActionDelegate = taDelegate
        dataActionDelegate = daDelegage
    }

    private fun setUpView() {
        recyclerView.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
        adapter = TaskAdapter(
            touchActionDelegate = touchActionDelegate,
            dataActionDelegate = dataActionDelegate
        )
        recyclerView.adapter = adapter
        fabAdd.setOnClickListener {
            touchActionDelegate.onAddButtonClicked(NavigationActivity.FRAGMENT_VALUE_TASK);
        }
    }

    fun updateList(list: List<Task>) {
        adapter.updateList(list)
    }

}