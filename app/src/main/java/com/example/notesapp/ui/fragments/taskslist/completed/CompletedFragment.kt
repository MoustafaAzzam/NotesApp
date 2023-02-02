package com.example.notesapp.ui.fragments.taskslist.completed

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
import com.example.notesapp.databinding.FragmentCompletedBinding
import com.example.notesapp.model.Task
import com.example.notesapp.ui.adapter.CompletedTaskAdapter
import com.example.notesapp.ui.adapter.TasksClickListener
import com.example.notesapp.viewmodel.TasksViewModel


class CompletedFragment : Fragment() ,TasksClickListener{

    private var binding : FragmentCompletedBinding?=null
    private lateinit var tasksViewModel : TasksViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCompletedBinding.inflate(inflater,container,false)

        tasksViewModel = ViewModelProvider(this)[TasksViewModel::class.java]

        val adapter= CompletedTaskAdapter(this)
        val recyclerView = binding?.CompletedRecyclerView
        recyclerView?.adapter =adapter
        recyclerView?.layoutManager= LinearLayoutManager(requireContext())

        tasksViewModel.completedTasks.observe(viewLifecycleOwner){task->
            adapter.setData(task)
        }
        return binding?.root
    }

    override fun itemOnClick(view: View, task: Task) {
        when(view.id){
            R.id.Task_Completed_Row_Edit_Btn ->{

                val action = CompletedFragmentDirections.actionCompletedToUpdateFragment(task)
                binding?.root?.findNavController()?.navigate(action)
                Toast.makeText(requireContext(),"Edit Button in Completed tasks"+task.title, Toast.LENGTH_LONG).show()
            }

            R.id.Task_Completed_Row_undo_Btn ->{
                tasksViewModel.updateTask(Task(task.id,task.title,task.description,false,task.dueDate))
                Toast.makeText(requireContext(),"Undo Button"+task.description, Toast.LENGTH_LONG).show()
            }

        }
    }

}