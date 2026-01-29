package com.example.cleanarchitecture.data.local.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.cleanarchitecture.data.local.Entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {

    @Insert
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM user_table")
    fun getAllUser() : Flow<List<UserEntity>>

    @Update
    suspend fun updateUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity)
}