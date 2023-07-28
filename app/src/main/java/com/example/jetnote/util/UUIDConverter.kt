package com.example.jetnote.util

import androidx.room.TypeConverter
import java.util.UUID

class UUIDConverter {

    @TypeConverter
    fun fromUUUD(uuid: UUID): String?{
        return uuid.toString()
    }

    fun uuidFromString(string: String?): UUID?{
        return UUID.fromString(string)
    }
}