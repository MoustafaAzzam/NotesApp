package com.example.notesapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.notesapp.model.Task

@Database(entities = [Task::class] , version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class TasksDataBase:RoomDatabase() {
    abstract fun tasksDao(): TasksDao

    companion object{

        @Volatile
        private var Instance : TasksDataBase?=null

        @Synchronized
        fun getDataBase(context: Context): TasksDataBase {
            var instance = Instance
            if (instance ==null){
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    TasksDataBase::class.java,
                    "tasks_db"
                ).fallbackToDestructiveMigration()
                    .build()
                Instance = instance
                return instance
            }
            return instance
        }

    }
}