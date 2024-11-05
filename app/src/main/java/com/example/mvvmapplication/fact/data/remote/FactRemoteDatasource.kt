package com.example.mvvmapplication.fact.data.remote

import com.example.mvvmapplication.fact.model.Fact

interface FactRemoteDatasource {
    suspend fun getFact(): Fact
}