package com.beloushkin.android.learn.notes.views

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.beloushkin.android.learn.notes.R
import com.beloushkin.android.learn.notes.models.Task
import kotlinx.android.synthetic.main.item_task.view.*

class TaskView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1
) : ConstraintLayout(context, attrs, defStyleAttr) {

    lateinit var task: Task

    fun initView(task: Task, todoCheckedCallBack: (Int, Boolean) -> Unit, deleteCallback: () -> Unit) {
        resetChildViews()
        this.task = task
        initTaskLine(deleteCallback)
        addChildViews(todoCheckedCallBack)
    }

    private fun resetChildViews() {
        todoContainer.removeAllViewsInLayout()
    }

    private fun initTaskLine(deleteCallback: () -> Unit) {
        titleView.text = task.title

        imageButton.setOnClickListener {
            deleteCallback.invoke()
        }
    }

    private fun addChildViews(todoCheckedCallBack: (Int, Boolean) -> Unit) {
        task.todos?.forEachIndexed { todoIndex, todo ->
            val todoView = (LayoutInflater.from(context).inflate(R.layout.view_todo,todoContainer,false) as TodoView).apply {
                initView(todo) { isChecked ->
                    todoCheckedCallBack.invoke(todoIndex, isChecked)
                    if (isTaskComplete()) {
                        this@TaskView.titleView.setStrikeThrough()
                    } else {
                        this@TaskView.titleView.removeStrikeThrough()
                    }
                }
            }
            todoContainer.addView(todoView)
        }
    }

    private fun isTaskComplete(): Boolean = task.todos!!.filter { !it.isComplete }.isEmpty()

}