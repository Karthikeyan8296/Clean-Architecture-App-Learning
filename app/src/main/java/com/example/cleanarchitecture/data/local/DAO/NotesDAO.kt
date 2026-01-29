package com.example.cleanarchitecture.data.local.DAO

import androidx.room.Dao
import androidx.room.Insert
import com.example.cleanarchitecture.data.local.Entity.NoteEntity

@Dao
interface NotesDAO {

    @Insert
    suspend fun insertNote(note: NoteEntity)
}