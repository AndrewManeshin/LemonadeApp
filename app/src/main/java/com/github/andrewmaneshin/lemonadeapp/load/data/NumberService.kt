package com.github.andrewmaneshin.lemonadeapp.load.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NumberService {

    @GET("random")
    fun number(
        @Query("min") min: Int = 1,
        @Query("max") max: Int = 10,
        @Query("count") count: Int = 1
    ): Call<Array<Int>>
}