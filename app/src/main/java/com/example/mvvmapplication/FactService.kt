package com.example.mvvmapplication

import retrofit2.http.GET

interface FactService {
    @GET("/api/v2/facts/random")
    suspend fun getRandomFact(): Fact
}