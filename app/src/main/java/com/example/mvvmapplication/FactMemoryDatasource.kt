package com.example.mvvmapplication

class FactMemoryDatasource {
    private var currentFact: Fact? = null

    fun save(fact: Fact) {
        currentFact = fact
    }

    fun get() = currentFact
}