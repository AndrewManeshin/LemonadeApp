package com.github.andrewmaneshin.lemonadeapp.load.di

import com.github.andrewmaneshin.lemonadeapp.AbstractProvideViewModel
import com.github.andrewmaneshin.lemonadeapp.Core
import com.github.andrewmaneshin.lemonadeapp.Module
import com.github.andrewmaneshin.lemonadeapp.ProvideViewModel
import com.github.andrewmaneshin.lemonadeapp.load.presentation.LoadViewModel

class ProvideLoadViewModel(core: Core, next: ProvideViewModel) :
    AbstractProvideViewModel(core, next, LoadViewModel::class.java) {

    override fun module(): Module<*> = LoadModule(core)
}