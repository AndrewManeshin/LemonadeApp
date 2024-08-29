package com.github.andrewmaneshin.lemonadeapp

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.github.andrewmaneshin.lemonadeapp.view.description.UpdateTextRes
import com.github.andrewmaneshin.lemonadeapp.view.image.UpdateImageRes

interface LemonadeUiState {

    fun update(
        image: UpdateImageRes,
        descriptionTextView: UpdateTextRes
    )

    data class Base(
        @DrawableRes private val imageResId: Int,
        @StringRes private val descriptionResId: Int
    ) : LemonadeUiState {

        override fun update(
            image: UpdateImageRes,
            descriptionTextView: UpdateTextRes
        ) {
            image.update(imageResId)
            descriptionTextView.update(descriptionResId)
        }
    }

    object Empty : LemonadeUiState {
        override fun update(image: UpdateImageRes, descriptionTextView: UpdateTextRes) = Unit
    }
}