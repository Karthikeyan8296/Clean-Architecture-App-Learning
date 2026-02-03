package com.example.cleanarchitecture.presentation.screens.Home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecture.data.local.Entity.UserEntity
import com.example.cleanarchitecture.data.repository.UserRepositoryImpl
import com.example.cleanarchitecture.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor (
    private val userRepository: UserRepository
) : ViewModel() {

    //get all the user data
    val user: StateFlow<List<UserEntity>> = userRepository.
        //stateIn - Convert this Flow into a StateFlow and keep its latest value in memory
    getUsers().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    //insert User
    fun insertUser(name: String, age: Int) {
        viewModelScope.launch {
            userRepository.insertUser(UserEntity(name = name, age = age))
        }
    }

    fun updateUser(user: UserEntity){
        viewModelScope.launch {
            userRepository.updateUser(user)
        }
    }

    fun deleteUser(user: UserEntity){
        viewModelScope.launch {
            userRepository.deleteUser(user)
        }
    }
}