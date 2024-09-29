package com.github.andrewmaneshin.lemonadeapp.lemonade.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.github.andrewmaneshin.lemonadeapp.lemonade.presentation.view.description.UpdateTextRes
import com.github.andrewmaneshin.lemonadeapp.lemonade.presentation.view.image.UpdateImageButton
import com.github.andrewmaneshin.lemonadeapp.load.presentation.NavigateToLoad

interface LemonadeUiState {

    fun update(
        image: UpdateImageButton,
        descriptionTextView: UpdateTextRes
    )

    fun navigate(navigateToLoad: NavigateToLoad) = Unit

    abstract class Abstract(
        @DrawableRes private val imageResId: Int,
        @StringRes private val descriptionResId: Int
    ) : LemonadeUiState {
        override fun update(
            image: UpdateImageButton,
            descriptionTextView: UpdateTextRes
        ) {
            image.update(imageResId)
            descriptionTextView.update(descriptionResId)
        }
    }

    data class Squeeze(
        @DrawableRes private val imageResId: Int,
        @StringRes private val descriptionResId: Int
    ) : Abstract(imageResId, descriptionResId) {

        override fun navigate(navigateToLoad: NavigateToLoad) = navigateToLoad.navigateToLoad()
    }

    data class Base(
        @DrawableRes private val imageResId: Int,
        @StringRes private val descriptionResId: Int
    ) : Abstract(imageResId, descriptionResId)

    object Empty : LemonadeUiState {
        override fun update(image: UpdateImageButton, descriptionTextView: UpdateTextRes) = Unit
    }
}