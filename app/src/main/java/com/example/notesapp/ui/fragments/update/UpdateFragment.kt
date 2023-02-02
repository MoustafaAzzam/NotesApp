package com.example.notesapp.ui.fragments.update

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.notesapp.databinding.FragmentUpdateBinding
import com.example.notesapp.model.Task
import com.example.notesapp.viewmodel.TasksViewModel
import java.time.LocalDate
import java.util.*


class UpdateFragment : Fragment() {

    private  var binding : FragmentUpdateBinding?=null
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var tasksViewModel : TasksViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(inflater,container,false)

        tasksViewModel = ViewModelProvider(this)[TasksViewModel::class.java]
        displayData()

        binding?.TaskDateUpdateFET?.setOnClickListener {
            setUpDatePicker()
        }

        binding?.buttonTaskUpdate?.setOnClickListener {
            updateTask()
        }


        Toast.makeText(requireContext(),"Safe Args"+args.currentTask.title,Toast.LENGTH_LONG).show()
        return binding?.root
    }

    private fun updateTask() {
        if(checkTitle()&&checkDescription()&&checkDueDate()){
            val title = binding?.TaskTitleUpdateET?.text.toString()
            val description = binding?.TaskUpdateDescriptionET?.text.toString()
            val dueDate = LocalDate.parse(binding?.TaskDateUpdateFET?.text.toString())
            tasksViewModel.updateTask(Task(args.currentTask.id,title,description,args.currentTask.status,LocalDate.parse(dueDate.toString())))
            Toast.makeText(requireContext(),"Task Updated successfully",Toast.LENGTH_LONG).show()
            parentFragmentManager.popBackStackImmediate()

        }else{
            Toast.makeText(requireContext(),"Kindly fill all fields",Toast.LENGTH_LONG).show()
        }
    }

    private fun displayData() {
        binding?.TaskDateUpdateFET?.setText(args.currentTask.dueDate.toString())
        binding?.TaskTitleUpdateET?.setText(args.currentTask.title)
        binding?.TaskUpdateDescriptionET?.setText(args.currentTask.description)
    }

    private fun setUpDatePicker() {
        val calender = Calendar.getInstance()
        val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH)
        val day = calender.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(requireContext(), { _, Year, Month, Day ->
            binding?.TaskDateUpdateFET?.setText(LocalDate.of(Year,Month+1,Day).toString())

        },year,month,day)
        datePicker.show()

    }


    private fun checkDueDate(): Boolean {
        return if (binding?.TaskTitleUpdateET?.text?.isEmpty() == true){
            binding?.TaskDateUpdateETLayout?.helperText="Enter Due Date"
            false
        } else{
            true
        }

    }

    private fun checkDescription(): Boolean {
        return if (binding?.TaskUpdateDescriptionET?.text?.isEmpty() == true){
            binding?.TaskUpdateDescriptionETLayoutUF?.helperText="Enter Description"
            false
        } else{
            true
        }
    }

    private fun checkTitle(): Boolean {
        return if (binding?.TaskTitleUpdateET?.text?.isEmpty() == true){
            binding?.TaskTitleUpdateETLayoutUF?.helperText="Enter Title"
            false
        } else{
            true
        }
    }


}