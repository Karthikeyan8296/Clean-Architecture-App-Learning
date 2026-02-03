package com.example.cleanarchitecture.data.repository

import com.example.cleanarchitecture.data.local.DAO.UserDAO
import com.example.cleanarchitecture.data.local.Entity.UserEntity
import com.example.cleanarchitecture.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDAO: UserDAO
) : UserRepository{
    override suspend fun insertUser(user: UserEntity){
        userDAO.insertUser(user)
    }

    override fun getUsers(): Flow<List<UserEntity>>{
        return userDAO.getAllUser()
    }

    override suspend fun updateUser(user: UserEntity){
        userDAO.updateUser(user)
    }

    override suspend fun deleteUser(user: UserEntity){
        userDAO.deleteUser(user)
    }
}
