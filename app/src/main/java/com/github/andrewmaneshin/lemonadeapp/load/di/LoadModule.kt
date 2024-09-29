package com.github.andrewmaneshin.lemonadeapp.load.di

import com.github.andrewmaneshin.lemonadeapp.Core
import com.github.andrewmaneshin.lemonadeapp.Module
import com.github.andrewmaneshin.lemonadeapp.load.data.LoadRepository
import com.github.andrewmaneshin.lemonadeapp.load.data.NumberService
import com.github.andrewmaneshin.lemonadeapp.load.data.RunAsync
import com.github.andrewmaneshin.lemonadeapp.load.presentation.LoadViewModel
import com.github.andrewmaneshin.lemonadeapp.load.presentation.UiObservable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class LoadModule(private val core: Core) : Module<LoadViewModel> {

    override fun viewModel(): LoadViewModel {
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .retryOnConnectionFailure(true)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.randomnumberapi.com/api/v1.0/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return LoadViewModel(
            LoadRepository.Base(
                retrofit.create(NumberService::class.java),
                core.countCache
            ),
            UiObservable.Base(),
            RunAsync.Base()
        )
    }
}