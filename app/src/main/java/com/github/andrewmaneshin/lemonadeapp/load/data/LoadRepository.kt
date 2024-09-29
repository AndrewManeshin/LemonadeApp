package com.github.andrewmaneshin.lemonadeapp.load.data

import com.github.andrewmaneshin.lemonadeapp.IntCache

interface LoadRepository {

    fun load(): LoadResult

    class Base(
        private val service: NumberService,
        private val intCache: IntCache
    ) : LoadRepository {

        override fun load(): LoadResult {
            try {
                val result = service.number().execute()
                if (result.isSuccessful) {
                    val data = result.body()!!.first()
                    intCache.save(data)
                    return LoadResult.Success
                } else {
                    return LoadResult.Error("Something went wrong...")
                }
            } catch (e: Exception) {
                return (LoadResult.Error(e.message ?: "error"))
            }
        }
    }
}