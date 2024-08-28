package com.github.andrewmaneshin.lemonadeapp

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface LemonadeUiState {

    data class Base(
        @DrawableRes private val imageResId: Int,
        @StringRes private val descriptionResId: Int
    ) : LemonadeUiState

    object Empty : LemonadeUiState
}