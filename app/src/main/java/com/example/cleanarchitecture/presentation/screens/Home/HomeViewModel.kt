package com.example.cleanarchitecture.presentation.screens.Home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel(){

    //it is private because it should be accessed only inside the view model
    private val _counter = MutableLiveData(0)

    //observed data to the UI
    val counter: LiveData<Int> = _counter

    fun increaseValue() {
        val current = _counter.value ?: 0
        _counter.value = current + 1
    }
}