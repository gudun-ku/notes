package com.beloushkin.android.learn.notes.views

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.beloushkin.android.learn.notes.models.Todo
import kotlinx.android.synthetic.main.view_todo.view.*

class TodoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1
) : ConstraintLayout(context, attrs, defStyleAttr) {

    fun initView(todo: Todo,callback: ((Boolean) -> Unit)? = null) {

        descriptionView.text = todo.description
        completeCheckBox.isChecked = todo.isComplete

        if (todo.isComplete) {
            descriptionView.paintFlags = descriptionView!!.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }

        setUpCheckStateListener(todo,callback)
    }

    fun setUpCheckStateListener(todo: Todo, callback: ((Boolean) -> Unit)? = null) {

       completeCheckBox!!.setOnCheckedChangeListener { _ , isChecked ->
           todo.isComplete = isChecked
           callback?.invoke(isChecked)
           if (isChecked) {
               descriptionView.setStrikeThrough()
           } else {
               descriptionView.removeStrikeThrough()
           }
       }
    }
}
