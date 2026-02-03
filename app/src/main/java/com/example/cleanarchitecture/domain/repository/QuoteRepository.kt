package com.example.cleanarchitecture.domain.repository

import com.example.cleanarchitecture.data.remote.DTO.QuoteDTO
import com.example.cleanarchitecture.data.repository.Result

interface QuoteRepository {
    suspend fun getQuote(id: Int): Result<QuoteDTO>
}