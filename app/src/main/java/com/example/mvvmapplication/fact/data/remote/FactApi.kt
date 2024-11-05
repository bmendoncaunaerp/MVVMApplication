package com.example.mvvmapplication.fact.data.remote

import com.example.mvvmapplication.fact.model.Fact
import retrofit2.Response
import retrofit2.http.GET

interface FactApi {

    @GET("/api/v2/facts/random")
    suspend fun getUsers(): Response<Fact>
}