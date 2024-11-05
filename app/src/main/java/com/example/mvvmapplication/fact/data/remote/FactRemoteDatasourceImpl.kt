package com.example.mvvmapplication.fact.data.remote

import com.example.mvvmapplication.fact.model.Fact

class FactRemoteDatasourceImpl(
    private val factApi: FactApi
): FactRemoteDatasource {
    override suspend fun getFact(): Fact {
        val response = factApi.getUsers()
        if(response.isSuccessful && response.body() != null) {
            return response.body()!!
        } else {
            throw ApiException()
        }
    }
}