package com.example.notesapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.local.TasksDataBase
import com.example.notesapp.model.Task
import com.example.notesapp.repository.TasksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TasksViewModel(application: Application):AndroidViewModel(application) {

    val allTasks : LiveData<List<Task>>
    val currentTasks : LiveData<List<Task>>
    val completedTasks : LiveData<List<Task>>
    private val repository : TasksRepository

    init {
        val tasksDao = TasksDataBase.getDataBase(application).tasksDao()
        repository= TasksRepository(tasksDao)
        allTasks = repository.allTasks
        currentTasks = repository.currentTasks
        completedTasks = repository.completedTasks
    }


    fun addTask(task: Task){

        viewModelScope.launch(Dispatchers.IO) {
            repository.addTask(task)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTask(task)
        }

    }

    fun deleteTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTask(task)
        }

    }

    fun deleteAllTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllTasks()
        }
    }

    fun deleteAllCurrentTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllCurrentTasks()
        }
    }
    fun deleteAllCompletedTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllCompletedTasks()
        }
    }

}