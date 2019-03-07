package com.beloushkin.android.learn.notes.database

import androidx.room.*
import com.beloushkin.android.learn.notes.models.Task
import com.beloushkin.android.learn.notes.models.TaskEntity
import com.beloushkin.android.learn.notes.models.Todo

@Dao
interface TaskDAO {

    @Insert
    fun addTask(taskEntity: TaskEntity)

    @Insert
    fun addTodo(todo: Todo)

    @Update
    fun updateTask(taskEntity: TaskEntity)

    @Update
    fun updateTodo(todo: Todo)

    @Delete
    fun deleteTask(taskEntity: TaskEntity)

    @Query("SELECT * FROM tasks")
    fun retrieveTasks(): MutableList<Task>

}