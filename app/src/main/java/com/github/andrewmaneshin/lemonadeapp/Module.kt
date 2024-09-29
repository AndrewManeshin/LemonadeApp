package com.github.andrewmaneshin.lemonadeapp

interface Module<T : MyViewModel> {

    fun viewModel(): T
}