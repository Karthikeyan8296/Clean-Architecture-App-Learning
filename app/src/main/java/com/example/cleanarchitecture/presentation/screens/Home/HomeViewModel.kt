package com.example.cleanarchitecture.presentation.screens.Home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {

    //it is private because it should be accessed only inside the view model
    private var _counter = MutableStateFlow(0)

    //observed data to the UI
    val counter = _counter.asStateFlow()

    fun increaseValue() {
        _counter.value += 1
    }
}