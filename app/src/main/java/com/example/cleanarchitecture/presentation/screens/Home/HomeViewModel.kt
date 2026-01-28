package com.example.cleanarchitecture.presentation.screens.Home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {

    //it is private because it should be accessed only inside the view model
    private var _counter = MutableStateFlow(0)

    //observed data to the UI
    val counter = _counter.asStateFlow()

    private val _event = MutableSharedFlow<Int>()
    val event = _event.asSharedFlow()

    fun increaseValue() {
        _counter.value += 1
    }
}