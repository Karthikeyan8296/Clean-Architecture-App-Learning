package com.example.cleanarchitecture.data.repository

import com.example.cleanarchitecture.data.local.DAO.UserDAO
import com.example.cleanarchitecture.data.local.Entity.UserEntity
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(
    private val userDAO: UserDAO
){
    suspend fun insertUser(user: UserEntity){
        userDAO.insertUser(user)
    }

    fun getUsers(): Flow<List<UserEntity>>{
        return userDAO.getAllUser()
    }

    suspend fun updateUser(user: UserEntity){
        userDAO.updateUser(user)
    }

    suspend fun deleteUser(user: UserEntity){
        userDAO.deleteUser(user)
    }
}
