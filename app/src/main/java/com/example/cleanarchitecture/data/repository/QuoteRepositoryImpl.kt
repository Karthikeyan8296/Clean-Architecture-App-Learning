package com.example.cleanarchitecture.data.repository

import com.example.cleanarchitecture.data.remote.DTO.QuoteDTO
import com.example.cleanarchitecture.data.remote.api.QuoteAPI
import com.example.cleanarchitecture.domain.repository.QuoteRepository
import javax.inject.Inject

class QuoteRepositoryImpl @Inject constructor(private val api: QuoteAPI) : QuoteRepository {
    override suspend fun getQuote(id: Int): Result<QuoteDTO> {
        return try {
            val response = api.getQuotes(id)

            if (response.isSuccessful) {
                val body = response.body()

                if (body != null) {
                    Result.Success(body)
                } else {
                    Result.Error("Response body is null")
                }
            } else {
                Result.Error("Request failed with code ${response.code()}")
            }
        } catch (e: Exception) {
            Result.Error("error occurred: ${e.message}")
        }
    }
}

sealed class Result<out T>{
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()
}