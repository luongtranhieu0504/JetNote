package com.example.jetnote.model

import android.service.quicksettings.Tile
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.time.LocalDateTime
import java.util.Date
import java.util.UUID

@Entity(tableName = "notes_tbl")
data class Note(
        @PrimaryKey
        var id: UUID = UUID.randomUUID(),

        @ColumnInfo(name = "notes_title")
        var tile: String,

        @ColumnInfo(name = "notes_description")
        var description: String,

        @ColumnInfo(name = "notes_entryDate")
        val entryDate: Date = Date.from(Instant.now())
)
