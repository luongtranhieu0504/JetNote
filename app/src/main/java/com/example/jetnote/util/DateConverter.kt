package com.example.jetnote.util

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {
    @TypeConverter
    fun timeStampFromDate(date: Date): Long{
        return date.time
    }

    @TypeConverter
    fun dateStampFromTime(time: Long): Date?{
        return Date(time)
    }

}