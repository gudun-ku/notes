package com.beloushkin.android.learn.notes.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beloushkin.android.learn.notes.R
import com.beloushkin.android.learn.notes.models.Task
import kotlinx.android.synthetic.main.item_task.view.*

class TaskAdapter(
    private val taskList: MutableList<Task> = mutableListOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false))

    override fun getItemCount(): Int = taskList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).onBind(taskList.get(position))
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(task: Task) {
            view.taskTitleView.text = task.title
        }
    }
}