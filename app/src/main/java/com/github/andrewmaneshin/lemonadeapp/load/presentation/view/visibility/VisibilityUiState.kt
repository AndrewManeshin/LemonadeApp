package com.github.andrewmaneshin.lemonadeapp.load.presentation.view.visibility

import android.view.View
import java.io.Serializable

interface VisibilityUiState : Serializable {

    fun update(updateVisibility: UpdateVisibility)

    abstract class Abstract(private val visibility: Int) : VisibilityUiState {

        override fun update(updateVisibility: UpdateVisibility) {
            updateVisibility.update(visibility)
        }
    }

    object Visible : Abstract(View.VISIBLE)
    object Gone : Abstract(View.GONE)
}