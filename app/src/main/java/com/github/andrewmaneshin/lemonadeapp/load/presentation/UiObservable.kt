package com.github.andrewmaneshin.lemonadeapp.load.presentation

interface UiObservable {

    fun register(observer: (LoadUiState) -> Unit)

    fun unregister()

    fun postUiState(uiState: LoadUiState)
}