package com.github.andrewmaneshin.lemonadeapp.load.presentation.view.error

interface UpdateError {

    fun update(uiState: ErrorUiState)

    fun updateText(textResId: Int)

    fun updateVisibility(visibility: Int)
}