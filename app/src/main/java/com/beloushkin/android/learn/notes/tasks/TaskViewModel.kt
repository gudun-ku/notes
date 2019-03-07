package com.beloushkin.android.learn.notes.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beloushkin.android.learn.notes.foundations.ApplicationScope
import com.beloushkin.android.learn.notes.models.Task
import toothpick.Toothpick
import javax.inject.Inject

class TaskViewModel : ViewModel(), TaskListViewContract  {

    @Inject
    lateinit var model: ITaskModel

    private val _taskListLiveData: MutableLiveData<MutableList<Task>> = MutableLiveData()
    val taskListLiveData: LiveData<MutableList<Task>> = _taskListLiveData


    init {
        Toothpick.inject(this, ApplicationScope.scope)
        _taskListLiveData.postValue(model.getFakeData())
    }

    override fun onTodoUpdated(taskIndex: Int, todoIndex: Int, isComplete: Boolean) {
        _taskListLiveData.value?.let {taskList ->
            val task = taskList[taskIndex]
            task.todos?.let {todoList ->
                val todo = todoList[todoIndex]
                todo.apply {
                    this.isComplete = isComplete
                    this.taskId = task.uid
                }
                model.updateTodo(todo) {
                    loadData()
                }
            }
        }
    }

    fun loadData() {
        _taskListLiveData.postValue(model.retrieveTasks().toMutableList())
    }

    override fun onTaskDeleted(taskIndex: Int) {
        _taskListLiveData.value?.let {
            model.deleteTask(it[taskIndex]) {
                loadData()
            }
        }
    }
}