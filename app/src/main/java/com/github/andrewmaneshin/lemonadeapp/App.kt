package com.github.andrewmaneshin.lemonadeapp

import android.app.Application
import android.content.Context
import android.os.Build
import com.github.andrewmaneshin.lemonadeapp.lemonade.data.LemonadeRepository
import com.github.andrewmaneshin.lemonadeapp.lemonade.data.SqueezeStrategy
import com.github.andrewmaneshin.lemonadeapp.lemonade.presentation.LemonadeViewModel
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

class App : Application() {

    lateinit var lemonadeViewModel: LemonadeViewModel
    lateinit var loadViewModel: LoadViewModel

    override fun onCreate() {
        super.onCreate()

        val sharedPreferences = applicationContext.getSharedPreferences(
            R.string.app_name.toString(),
            Context.MODE_PRIVATE
        )

        Build.TYPE

        val cache = IntCache.Base(sharedPreferences, "times", (1..3).random())
        lemonadeViewModel = LemonadeViewModel(
            LemonadeRepository.Base(
                IntCache.Base(sharedPreferences, "index", 0),
//                SqueezeStrategy.Test
                SqueezeStrategy.Base(cache) //todo build version
            )
        )

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

        loadViewModel = LoadViewModel(
            LoadRepository.Base(
                retrofit.create(NumberService::class.java),
                cache
            ),
            UiObservable.Base(),
            RunAsync.Base()
        )
    }
}