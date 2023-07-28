package com.example.jetnote.data

import com.example.jetnote.model.Note

class NotesDataSource {
    fun loadNotes(): List<Note> {
        return listOf(
            Note(tile = "Hieu", description = "Hello xin chào cả nhà"),
            Note(tile = "Hieu", description = "Hello xin chào cả nhà"),
            Note(tile = "Hieu", description = "Hello xin chào cả nhà"),
            Note(tile = "Hieu", description = "Hello xin chào cả nhà"),
            Note(tile = "Hieu", description = "Hello xin chào cả nhà"),
        )
    }

}
