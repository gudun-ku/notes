package com.beloushkin.android.learn.notes.create

import android.content.Context
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import com.beloushkin.android.learn.notes.R
import com.beloushkin.android.learn.notes.foundations.ApplicationScope
import com.beloushkin.android.learn.notes.foundations.NullFieldChecker
import com.beloushkin.android.learn.notes.foundations.StateChangeTextWatcher
import com.beloushkin.android.learn.notes.models.Task
import com.beloushkin.android.learn.notes.models.Todo
import com.beloushkin.android.learn.notes.tasks.ITaskModel
import com.beloushkin.android.learn.notes.tasks.TaskLocalModel
import com.beloushkin.android.learn.notes.tasks.successCallback
import com.beloushkin.android.learn.notes.views.CreateTodoView
import kotlinx.android.synthetic.main.fragment_create_task.*
import kotlinx.android.synthetic.main.view_create_task.view.*
import kotlinx.android.synthetic.main.view_create_todo.view.*
import toothpick.Toothpick
import javax.inject.Inject

private const val MAX_TODO_COUNT = 5

class CreateTaskFragment : Fragment() {

    @Inject
    lateinit var model: ITaskModel

    private var listener: OnFragmentInteractionListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toothpick.inject(this, ApplicationScope.scope)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_task, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createTaskView.taskEditText.addTextChangedListener(object: StateChangeTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                // add if s is not empty and s has changed -> non empty
                if(!s.isNullOrEmpty() && previousValue.isNullOrEmpty()) {
                    addTodoView()
                } else if (!previousValue.isNullOrEmpty() && s.isNullOrEmpty()) {
                    removeTodoView(containerView.getChildAt(containerView.childCount -1))
                }
                super.afterTextChanged(s)

            }
        })

    }

    private fun addTodoView() {
        if (canAddTodos()) {
            val view =
                (LayoutInflater.from(context).inflate(R.layout.view_create_todo, containerView, false) as CreateTodoView).apply {
                    todoEditText.addTextChangedListener(object : StateChangeTextWatcher() {
                        override fun afterTextChanged(s: Editable?) {
                            if (!s.isNullOrEmpty() && previousValue.isNullOrEmpty()) {
                                addTodoView()
                            } else if (!previousValue.isNullOrEmpty() && s.isNullOrEmpty()) {
                                removeTodoView(this@apply)

                                if (containerView.childCount == MAX_TODO_COUNT) {
                                    addTodoView()
                                }
                            }
                            super.afterTextChanged(s)
                        }
                    })
                }

            containerView.addView(view)
        }
    }

    private fun removeTodoView(view: View) {
        containerView.removeView(view)
    }



    private fun canAddTodos() : Boolean = (containerView.childCount < MAX_TODO_COUNT + 1) &&
            !(containerView.getChildAt(containerView.childCount - 1) as NullFieldChecker).hasNullField()

    private fun isTaskEmpty(): Boolean = createTaskView.taskEditText.editableText.isNullOrEmpty()

    fun saveTask(callback:successCallback) {
        createTask()?.let{
            model.addTask(it) {
                //assume model always works
                callback.invoke(true)
            }
        } ?: callback.invoke(false)
    }

    fun createTask(): Task? {
        if (!isTaskEmpty()) {
            containerView.run {
                var taskField: String? = null
                var todoList: MutableList<Todo> = mutableListOf()

                for (i in 0 until containerView.childCount) {
                    if (i == 0) {
                        //task
                        taskField = containerView.getChildAt(i).taskEditText.editableText?.toString()
                    } else {
                        //todos
                        if (!containerView.getChildAt(i).todoEditText.editableText.isNullOrEmpty()) {
                            todoList.add(
                                Todo(containerView.getChildAt(i).todoEditText.editableText.toString())
                            )
                        }
                    }
                }
                return taskField?.let {
                    Task(taskField, todoList)
                }
            }
        } else {
            return null
        }
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction()
    }

    companion object {

        fun newInstance() = CreateTaskFragment()

    }
}
