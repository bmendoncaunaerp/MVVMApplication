package com.example.mvvmapplication

private const val EXPIRATION_TIME_MILLIS = 10000

class FactRepository {
    private val remote = FactRemoteDatasource()
    private val memory = FactMemoryDatasource()
    private var lastRequest: Long? = null

    suspend fun getFact(): Fact? {
        if (memory.get() != null &&
            (lastRequest ?: 0) + EXPIRATION_TIME_MILLIS > System.currentTimeMillis()
        ) {
            return memory.get()
        }

        return runCatching {
            remote.getFact()
        }.onSuccess {
            lastRequest = System.currentTimeMillis()
            memory.save(it)
        }.getOrElse {
            memory.get()
        }
    }
}