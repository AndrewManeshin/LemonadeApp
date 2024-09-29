package com.github.andrewmaneshin.lemonadeapp.load.data

interface RunAsync {

    fun <T : Any> handleAsync(heavyOperation: () -> T, uiUpdate: (T) -> Unit)
}