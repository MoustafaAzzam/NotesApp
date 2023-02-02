package com.example.notesapp.ui.home


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.notesapp.R
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.ui.add.TaskAddActivity
import com.example.notesapp.viewmodel.TasksViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var tasksViewModel : TasksViewModel
    private lateinit var  toggle : ActionBarDrawerToggle
    private lateinit var navController :NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tasksViewModel = ViewModelProvider(this)[TasksViewModel::class.java]

        setSupportActionBar(binding.TasksHomeToolBarId)//activityOcadminDashboardBinding.OCadminActivityAppbarmainIncludedLayout.OCadminActivityAppbarmainToolBarId)
        //toolbar.title=null
        supportActionBar?.setDisplayShowTitleEnabled(false)
        /*
        * Toolbar
        * */

        /*
        * Bottom Navigation With Navigation Control
        * */

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.Tasks_Home_NavHostFragment_id) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.TasksHomeBottomNavViewId,navController)

        binding.TasksHomeFabButtonId.setOnClickListener {
            val intent = Intent(this,TaskAddActivity::class.java)
            startActivity(intent)
        }


}

    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.Tasks_Home_NavHostFragment_id) as NavHostFragment
        val navController = navHostFragment.navController
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}

