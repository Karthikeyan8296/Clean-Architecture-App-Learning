package com.example.cleanarchitecture.presentation.screens.Home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecture.data.repository.Result
import com.example.cleanarchitecture.domain.repository.QuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class QuoteState(
    val loading: Boolean = false,
    val quote: String = "",
    val author: String = "",
    val nextQuote: Int = 1
)

@HiltViewModel
class QuoteViewModel @Inject constructor(private val repository: QuoteRepository) : ViewModel() {
    private val _state = MutableStateFlow(QuoteState())
    val state = _state.asStateFlow()

    fun getQuote(id: Int) {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true)

            when (val result = repository.getQuote(id)) {
                is Result.Success -> {
                    _state.value = _state.value.copy(
                        loading = false,
                        quote = result.data.quote,
                        author = result.data.author,
                    )
                }

                is Result.Error -> {
                    _state.value = _state.value.copy(
                        loading = false, quote = result.message , author = ""
                    )
                    Log.d("HOHO", "Error: ${result.message}")
                }
            }
        }
    }

    fun loadQuoteNext() {
        val next = state.value.nextQuote + 1
        _state.value = state.value.copy(nextQuote = next)
        getQuote(next)
    }
}