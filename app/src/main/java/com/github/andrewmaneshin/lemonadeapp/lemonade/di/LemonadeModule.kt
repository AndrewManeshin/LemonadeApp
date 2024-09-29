package com.github.andrewmaneshin.lemonadeapp.lemonade.di

import com.github.andrewmaneshin.lemonadeapp.Core
import com.github.andrewmaneshin.lemonadeapp.IntCache
import com.github.andrewmaneshin.lemonadeapp.Module
import com.github.andrewmaneshin.lemonadeapp.lemonade.data.LemonadeRepository
import com.github.andrewmaneshin.lemonadeapp.lemonade.data.SqueezeStrategy
import com.github.andrewmaneshin.lemonadeapp.lemonade.presentation.LemonadeViewModel

class LemonadeModule(private val core: Core) : Module<LemonadeViewModel> {

    override fun viewModel(): LemonadeViewModel = LemonadeViewModel(
        LemonadeRepository.Base(
            IntCache.Base(core.sharedPreferences, "index", 0),
//                SqueezeStrategy.Test
            SqueezeStrategy.Base(core.countCache) //todo build version
        )
    )
}