package com.example.notesapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.databinding.TaskRowBinding
import com.example.notesapp.model.Task

class TaskAdapter(private val tasksClickListener: TasksClickListener) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private var taskList = emptyList<Task>()
    class TaskViewHolder (val binding : TaskRowBinding) : RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = TaskRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentTask = taskList[position]
        holder.binding.TitleRowTV.text=currentTask.title
        holder.binding.DescriptionRowTV.text=currentTask.description
        holder.binding.DuDateRowTV.text=currentTask.dueDate.toString()
        holder.binding.TaskRowCompleteBtn.setOnClickListener {
            Toast.makeText(it.context,""+currentTask.title,Toast.LENGTH_LONG).show()
            tasksClickListener.itemOnClick(holder.binding.TaskRowCompleteBtn,currentTask)
        }
        holder.binding.TaskRowEditBtn.setOnClickListener {
            tasksClickListener.itemOnClick(holder.binding.TaskRowEditBtn,currentTask)
            Toast.makeText(it.context,""+currentTask.title,Toast.LENGTH_LONG).show()
        }

        /*
        holder.binding.PersonIdTv.text = currentPerson.id.toString()
        holder.binding.PersonNameTv.text=currentPerson.name
        holder.binding.PersonJobTv.text=currentPerson.job
        holder.binding.PersonStatusTv.text=currentPerson.status
        holder.binding.PersonAddressStreetTv.text=currentPerson.personAddress.Street
        holder.binding.PersonAddressRegionTv.text=currentPerson.personAddress.Region
        */

        //Update Fragment
        /*
        holder.binding.personRowLayout.setOnClickListener {
            val action = ListPersonFragmentDirections.actionListPersonFragmentToUpdatePersonFragment(currentPerson)
            holder.binding.root.findNavController().navigate(action)
        }
        */
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