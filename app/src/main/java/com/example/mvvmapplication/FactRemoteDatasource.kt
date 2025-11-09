package com.example.mvvmapplication

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

class FactRemoteDatasource {

    val networkJson = Json { ignoreUnknownKeys = true }
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://uselessfacts.jsph.pl/")
        .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
        .build()

    private val service: FactService = retrofit.create(FactService::class.java)

    suspend fun getFact() = service.getRandomFact()
}