package com.github.andrewmaneshin.lemonadeapp.load.view.visibility

interface UpdateVisibility {

    fun update(state: VisibilityUiState)

    fun update(visibility: Int)
}