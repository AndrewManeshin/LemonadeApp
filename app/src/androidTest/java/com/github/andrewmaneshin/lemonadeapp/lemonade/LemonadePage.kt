package com.github.andrewmaneshin.lemonadeapp.lemonade

import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.github.andrewmaneshin.lemonadeapp.R

class LemonadePage {

    private val containerIdMatcher = withParent(withId(R.id.lemonadeContainer))
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
        descriptionUi.withText(R.string.tree_description)
    }

    fun assertSqueezeState() {
        imageButtonUi.withDrawable(R.drawable.lemon_squeeze)
        descriptionUi.withText(R.string.squeeze_description)
    }

    fun assertDrinkState() {
        imageButtonUi.withDrawable(R.drawable.lemon_drink)
        descriptionUi.withText(R.string.drink_description)
    }

    fun assertRestartState() {
        imageButtonUi.withDrawable(R.drawable.lemon_restart)
        descriptionUi.withText(R.string.restart_description)
    }
}