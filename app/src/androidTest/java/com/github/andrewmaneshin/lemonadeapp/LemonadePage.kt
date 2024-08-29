package com.github.andrewmaneshin.lemonadeapp

import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent

class LemonadePage {

    private val containerIdMatcher = withParent(withId(R.id.rootLayout))
    private val classTypeMatcher = withParent(isAssignableFrom(LinearLayout::class.java))
    private val imageButtonUi = ImageButtonUi(
        id = R.id.imageButton,
        containerIdMatcher = containerIdMatcher,
        classTypeMatcher = classTypeMatcher
    )
    private val descriptionUi = DescriptionUi(
        id = R.id.descriptionTextView,
        containerIdMatcher = containerIdMatcher,
        classTypeMatcher = classTypeMatcher
    )

    fun clickImage() {
        imageButtonUi.click()
    }

    fun assertTreeState() {
        imageButtonUi.withDrawable(R.drawable.lemon_tree)
        descriptionUi.withText(R.string.tree_description.toString())
    }

    fun assertSqueezeState() {
        imageButtonUi.withDrawable(R.drawable.lemon_squeeze)
        descriptionUi.withText(R.string.squeeze_description.toString())
    }

    fun assertDrinkState() {
        imageButtonUi.withDrawable(R.drawable.lemon_drink)
        descriptionUi.withText(R.string.drink_description.toString())
    }

    fun assertRestartState() {
        imageButtonUi.withDrawable(R.drawable.lemon_restart)
        descriptionUi.withText(R.string.restart_description.toString())
    }
}