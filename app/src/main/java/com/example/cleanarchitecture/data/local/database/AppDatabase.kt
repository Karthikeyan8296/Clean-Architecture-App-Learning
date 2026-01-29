package com.example.cleanarchitecture.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cleanarchitecture.data.local.DAO.NotesDAO
import com.example.cleanarchitecture.data.local.DAO.UserDAO
import com.example.cleanarchitecture.data.local.Entity.NoteEntity
import com.example.cleanarchitecture.data.local.Entity.UserEntity

@Database(
    entities = [UserEntity::class, NoteEntity::class],
    version = 6,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDAO

    abstract fun noteDao(): NotesDAO
}