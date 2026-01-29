package com.example.cleanarchitecture.di

import android.content.Context
import androidx.room.Room
import com.example.cleanarchitecture.data.local.DAO.UserDAO
import com.example.cleanarchitecture.data.local.database.AppDatabase

object DatabaseModule{
    fun providerDatabase(context: Context): AppDatabase{
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    fun providerUserDAO(context: Context): UserDAO{
        return providerDatabase(context).userDao()
    }
}