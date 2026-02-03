package com.example.cleanarchitecture.domain.repository

import com.example.cleanarchitecture.data.local.Entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun insertUser(user: UserEntity)

    fun getUsers(): Flow<List<UserEntity>>

    suspend fun updateUser(user: UserEntity)

    suspend fun deleteUser(user: UserEntity)
}