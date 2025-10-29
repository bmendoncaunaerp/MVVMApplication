package com.example.mvvmapplication

import retrofit2.http.GET

interface UselessFactService {
    @GET("/api/v2/facts/random")
    suspend fun getRandomFact(): Fact
}