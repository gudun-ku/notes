package com.beloushkin.android.learn.notes.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beloushkin.android.learn.notes.R
import com.beloushkin.android.learn.notes.foundations.BaseRecyclerAdapter
import com.beloushkin.android.learn.notes.models.Task
import com.beloushkin.android.learn.notes.navigation.NavigationActivity
import com.beloushkin.android.learn.notes.views.TaskView
import kotlinx.android.synthetic.main.view_add_button.view.*


class TaskAdapter(
    taskList: MutableList<Task> = mutableListOf(),
    val touchActionDelegate: TasksListFragment.TouchActionDelegate,
    val dataActionDelegate: TaskListViewContract
) : BaseRecyclerAdapter<Task>(taskList) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false))


    inner class TaskViewHolder(view: View) : BaseViewHolder<Task>(view) {
        override fun onBind(data: Task, listIndex: Int) {
            (view as TaskView).initView(
                task = data,
                todoCheckedCallBack = {todoIndex, isChecked ->
                    dataActionDelegate.onTodoUpdated(listIndex,todoIndex, isChecked)
                },
                deleteCallback = {
                    dataActionDelegate.onTaskDeleted(listIndex)
                })
        }
    }
}