package com.example.mvvmapplication

import retrofit2.Response
import retrofit2.http.GET

interface RandomFactsApi {

    @GET("/api/v2/facts/random")
    suspend fun getUsers(): Response<Fact>
}