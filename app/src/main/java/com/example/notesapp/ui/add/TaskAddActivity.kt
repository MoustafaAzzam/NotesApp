package com.example.notesapp.ui.add

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.databinding.ActivityTaskAddBinding
import com.example.notesapp.model.Task
import com.example.notesapp.viewmodel.TasksViewModel
import java.time.LocalDate
import java.util.*

class TaskAddActivity : AppCompatActivity() {
    private lateinit var binding : ActivityTaskAddBinding
    private lateinit var tasksViewModel : TasksViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityTaskAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tasksViewModel = ViewModelProvider(this)[TasksViewModel::class.java]

        binding.TaskDateET.setOnClickListener {
            setUpDatePicker()
        }

        binding.buttonTaskAdd.setOnClickListener {
           insertNewTask()
        }

    }

    private fun setUpDatePicker() {
        val calender = Calendar.getInstance()
        val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH)
        val day = calender.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(this, { _, Year, Month, Day ->
            binding.TaskDateET.setText(LocalDate.of(Year,Month+1,Day).toString())

        },year,month,day)
        datePicker.show()

    }

    private fun insertNewTask() {

        if(checkTitle()&&checkDescription()&&checkDueDate()){
            val title = binding.TaskTitleET.text.toString()
            val description = binding.TaskDescriptionET.text.toString()
            val dueDate = LocalDate.parse(binding.TaskDateET.text.toString())
            tasksViewModel.addTask(Task(0,title,description,false,LocalDate.parse(dueDate.toString())))
            Toast.makeText(this,"Task added successfully",Toast.LENGTH_LONG).show()
            finish()
        }else{
            Toast.makeText(this,"Kindly fill all fields",Toast.LENGTH_LONG).show()
        }


    }

    private fun checkDueDate(): Boolean {
        return if (binding.TaskDateET.text?.isEmpty() == true){
            binding.TaskDateETLayout.helperText="Enter Due Date"
            false
        } else{
            true
        }

    }

    private fun checkDescription(): Boolean {
        return if (binding.TaskDescriptionET.text?.isEmpty() == true){
            binding.TaskDescriptionETLayout.helperText="Enter Description"
            false
        } else{
            true
        }
    }

    private fun checkTitle(): Boolean {
        return if (binding.TaskTitleET.text?.isEmpty() == true){
            binding.TaskTitleETLayout.helperText="Enter Title"
            false
        } else{
            true
        }
    }

}