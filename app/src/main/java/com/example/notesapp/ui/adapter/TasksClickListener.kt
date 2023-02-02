package com.example.notesapp.ui.adapter

import android.view.View
import com.example.notesapp.model.Task

interface TasksClickListener {
    fun itemOnClick(view: View,task:Task)
}

