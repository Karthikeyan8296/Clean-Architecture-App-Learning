package com.example.cleanarchitecture.data.remote.DTO

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuoteDTO(
    val author: String = "", val id: Int = 1, val quote: String = ""
)