package com.example.notesapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.databinding.TaskCompletedRowBinding
import com.example.notesapp.model.Task

class CompletedTaskAdapter (private val tasksClickListener: TasksClickListener) : RecyclerView.Adapter<CompletedTaskAdapter.TaskViewHolder>() {

    private var taskList = emptyList<Task>()
    class TaskViewHolder (val binding : TaskCompletedRowBinding) : RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = TaskCompletedRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentTask = taskList[position]
        holder.binding.TitleCompletedRowTV.text=currentTask.title
        holder.binding.DescriptionCompletedRowTV.text=currentTask.description
        holder.binding.DuDateCompletedRowTV.text=currentTask.dueDate.toString()
        holder.binding.TaskCompletedRowUndoBtn.setOnClickListener {
            Toast.makeText(it.context,""+currentTask.title, Toast.LENGTH_LONG).show()
            tasksClickListener.itemOnClick(holder.binding.TaskCompletedRowUndoBtn,currentTask)
        }
        holder.binding.TaskCompletedRowEditBtn.setOnClickListener {
            tasksClickListener.itemOnClick(holder.binding.TaskCompletedRowEditBtn,currentTask)
            Toast.makeText(it.context,""+currentTask.title, Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setData(task: List<Task>){
        this.taskList=task
        notifyDataSetChanged()
    }

}