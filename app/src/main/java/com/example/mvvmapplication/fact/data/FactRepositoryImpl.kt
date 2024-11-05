package com.example.mvvmapplication.fact.data

import com.example.mvvmapplication.fact.data.remote.FactRemoteDatasource
import com.example.mvvmapplication.fact.model.Fact

class FactRepositoryImpl(
    private val factRemoteDatasource: FactRemoteDatasource
) : FactRepository {
    override suspend fun getFact(): Fact =
        runCatching {
            factRemoteDatasource.getFact()
        }.getOrDefault(
            Fact(
                id = "",
                text = "-",
                source = "fallback",
                sourceUrl = "",
                language = "",
                permalink = ""
            )
        )
}