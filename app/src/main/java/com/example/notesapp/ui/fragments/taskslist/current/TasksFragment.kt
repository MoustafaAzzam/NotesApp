package com.example.notesapp.ui.fragments.taskslist.current

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentTasksBinding
import com.example.notesapp.model.Task
import com.example.notesapp.ui.adapter.TaskAdapter
import com.example.notesapp.ui.adapter.TasksClickListener
import com.example.notesapp.ui.fragments.taskslist.completed.CompletedFragmentDirections
import com.example.notesapp.viewmodel.TasksViewModel

class TasksFragment : Fragment() , TasksClickListener {

    private  var binding: FragmentTasksBinding?=null
    private lateinit var tasksViewModel : TasksViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTasksBinding.inflate(inflater,container,false)

        tasksViewModel = ViewModelProvider(this)[TasksViewModel::class.java]

        val adapter= TaskAdapter(this)
        val recyclerView = binding?.TasksRecyclerView
        recyclerView?.adapter =adapter
        recyclerView?.layoutManager= LinearLayoutManager(requireContext())


        tasksViewModel.currentTasks.observe(viewLifecycleOwner){task->
            adapter.setData(task)
        }


        return binding?.root
    }

    override fun itemOnClick(view: View, task: Task) {
        when(view.id){
            R.id.Task_Row_Complete_Btn ->{
                tasksViewModel.updateTask(Task(task.id,task.title,task.description,true,task.dueDate))
            Toast.makeText(requireContext(),"Completed"+task.title,Toast.LENGTH_LONG).show()
            }

            R.id.Task_Row_Edit_Btn ->{
                val action = TasksFragmentDirections.actionTasksToUpdateFragment2(task)
                binding?.root?.findNavController()?.navigate(action)
                Toast.makeText(requireContext(),""+task.description,Toast.LENGTH_LONG).show()
            }

        }
    }


}