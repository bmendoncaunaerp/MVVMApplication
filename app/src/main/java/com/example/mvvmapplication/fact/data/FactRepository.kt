package com.example.mvvmapplication.fact.data

import com.example.mvvmapplication.fact.model.Fact

interface FactRepository {
    suspend fun getFact(): Fact
}