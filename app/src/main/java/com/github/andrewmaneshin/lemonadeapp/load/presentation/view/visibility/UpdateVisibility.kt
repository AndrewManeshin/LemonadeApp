package com.github.andrewmaneshin.lemonadeapp.load.presentation.view.visibility

interface UpdateVisibility {

    fun update(state: VisibilityUiState)

    fun update(visibility: Int)
}