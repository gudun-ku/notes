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

    fun initView(todo: Todo) {

        descriptionView.text = todo.description
        completeCheckBox.isChecked = todo.isComplete

        if (todo.isComplete) {
            descriptionView.paintFlags = descriptionView!!.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }

        setUpCheckStateListener()
    }

    private fun setUpCheckStateListener() {
       completeCheckBox!!.setOnCheckedChangeListener { _ , isChecked ->
           if (isChecked) {
               createStrikeThrough()
           } else {
               removeStrikeThrough()
           }
       }
    }

    private fun createStrikeThrough() {
        descriptionView.apply {
            paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
    }

    private fun removeStrikeThrough() {
        descriptionView.apply {
            paintFlags = paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}
