package com.github.andrewmaneshin.lemonadeapp.lemonade

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.github.andrewmaneshin.lemonadeapp.lemonade.presentation.view.image.LemonadeImageButton
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class ImageButtonUi(id: Int, containerIdMatcher: Matcher<View>?, classTypeMatcher: Matcher<View>?) {

    private val interaction = onView(
        allOf(
            withId(id),
            containerIdMatcher,
            classTypeMatcher,
            isAssignableFrom(LemonadeImageButton::class.java)
        )
    )

    fun click() {
        interaction.perform(ViewActions.click())
    }

    fun withDrawable(resId: Int) {
        interaction.check(matches(DrawableVectorMatcher(resId)))
    }
}