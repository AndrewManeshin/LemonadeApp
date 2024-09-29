package com.github.andrewmaneshin.lemonadeapp

import android.app.Application
import android.content.Context
import android.os.Build
import com.github.andrewmaneshin.lemonadeapp.lemonade.data.LemonadeRepository
import com.github.andrewmaneshin.lemonadeapp.lemonade.data.SqueezeStrategy
import com.github.andrewmaneshin.lemonadeapp.lemonade.presentation.LemonadeViewModel

class App : Application() {

    lateinit var viewModel: LemonadeViewModel

    override fun onCreate() {
        super.onCreate()

        val sharedPreferences = applicationContext.getSharedPreferences(
            R.string.app_name.toString(),
            Context.MODE_PRIVATE
        )

        Build.TYPE

        viewModel = LemonadeViewModel(
            LemonadeRepository.Base(
                IntCache.Base(
                    sharedPreferences, "index", 0
                ),
//                SqueezeStrategy.Test
                SqueezeStrategy.Base(
                    IntCache.Base(sharedPreferences, "times", (1..3).random()) //todo build version
                )
            )
        )
    }
}