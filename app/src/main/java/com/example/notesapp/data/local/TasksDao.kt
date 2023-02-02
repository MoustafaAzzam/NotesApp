package com.example.notesapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notesapp.model.Task

@Dao
interface TasksDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(task: Task)

    @Query("SELECT * FROM tasks_table ORDER BY dueDate ASC")
    fun getAllTasks() : LiveData<List<Task>>

    @Query("SELECT * FROM tasks_table WHERE status=1  ORDER BY dueDate ASC")
    fun getAllCompletedTasks() : LiveData<List<Task>>

    @Query("SELECT * FROM tasks_table WHERE status=0  ORDER BY dueDate ASC")
    fun getAllCurrentTasks() : LiveData<List<Task>>

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("DELETE  FROM tasks_table")
    suspend fun deleteAllTasks()

    @Query("DELETE  FROM tasks_table WHERE status = 0")
    suspend fun deleteAllCurrentTasks()

    @Query("DELETE  FROM tasks_table WHERE status = 1")
    suspend fun deleteAllCompletedTasks()

}