package com.example.cleanarchitecture.data.local.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User_Table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val age: Int
)
