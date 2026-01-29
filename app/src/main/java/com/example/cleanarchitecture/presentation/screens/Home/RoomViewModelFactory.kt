package com.example.cleanarchitecture.presentation.screens.Home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cleanarchitecture.data.repository.UserRepositoryImpl


//this is how we tell android, to create viewModel
class RoomViewModelFactory(
    private val userRepository: UserRepositoryImpl
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoomViewModel::class.java)) {
            return RoomViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}