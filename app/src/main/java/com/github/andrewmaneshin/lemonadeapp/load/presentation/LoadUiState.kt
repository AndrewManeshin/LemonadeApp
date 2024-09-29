package com.github.andrewmaneshin.lemonadeapp.load.presentation

interface LoadUiState {

    object Progress : LoadUiState

    object Success : LoadUiState

    data class Error(private val message: String) : LoadUiState
}