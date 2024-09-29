package com.github.andrewmaneshin.lemonadeapp.lemonade.di

import com.github.andrewmaneshin.lemonadeapp.AbstractProvideViewModel
import com.github.andrewmaneshin.lemonadeapp.Core
import com.github.andrewmaneshin.lemonadeapp.Module
import com.github.andrewmaneshin.lemonadeapp.ProvideViewModel
import com.github.andrewmaneshin.lemonadeapp.lemonade.presentation.LemonadeViewModel

class ProvideLemonadeViewModel(core: Core, next: ProvideViewModel) :
    AbstractProvideViewModel(core, next, LemonadeViewModel::class.java) {

    override fun module(): Module<*> = LemonadeModule(core)
}