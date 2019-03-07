package com.beloushkin.android.learn.notes.database

import androidx.room.*
import com.beloushkin.android.learn.notes.models.Task
import com.beloushkin.android.learn.notes.models.TaskEntity

@Dao
interface TaskDAO {

    @Insert
    fun addTask(taskEntity: TaskEntity)

    @Update
    fun updateTask(taskEntity: TaskEntity)

    @Delete
    fun deleteTask(taskEntity: TaskEntity)

    @Query("SELECT * FROM tasks")
    fun retrieveTasks(): MutableList<Task>

}