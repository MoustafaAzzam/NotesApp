package com.example.notesapp.data.local

import androidx.room.TypeConverter
import java.time.LocalDate
import java.util.Date

class DateConverter {

    @TypeConverter
    fun fromDate(date:LocalDate?): String {
        return date.toString()
    }
    @TypeConverter
    fun toDate(value :String?): LocalDate? {
        return value?.let { LocalDate.parse(it) }
    }

}