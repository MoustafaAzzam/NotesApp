package com.example.notesapp.repository

import androidx.lifecycle.LiveData
import com.example.notesapp.data.local.TasksDao
import com.example.notesapp.model.Task

class TasksRepository(private val tasksDao: TasksDao) {

    val allTasks : LiveData<List<Task>> = tasksDao.getAllTasks()
    val currentTasks : LiveData<List<Task>> = tasksDao.getAllCurrentTasks()
    val completedTasks : LiveData<List<Task>> = tasksDao.getAllCompletedTasks()

    suspend fun addTask(task: Task){
        tasksDao.addTask(task)
    }

    suspend fun updateTask(task: Task) {
        tasksDao.updateTask(task)
    }

    suspend fun deleteTask(task: Task) {
        tasksDao.deleteTask(task)

    }

    suspend fun deleteAllTasks() {
        tasksDao.deleteAllTasks()
    }

    suspend fun deleteAllCurrentTasks() {
        tasksDao.deleteAllCurrentTasks()
    }

    suspend fun deleteAllCompletedTasks() {
        tasksDao.deleteAllCompletedTasks()
    }

}