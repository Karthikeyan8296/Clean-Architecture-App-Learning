package com.example.cleanarchitecture.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cleanarchitecture.data.local.DAO.UserDAO
import com.example.cleanarchitecture.data.local.Entity.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 4,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDAO
}