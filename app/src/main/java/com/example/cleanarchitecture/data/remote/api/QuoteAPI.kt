package com.example.cleanarchitecture.data.remote.api

import com.example.cleanarchitecture.data.remote.DTO.QuoteDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface QuoteAPI {
    @GET("/quotes/{id}")
    suspend fun getQuotes(
        @Path("id") id: Int
    ): Response<QuoteDTO>
}