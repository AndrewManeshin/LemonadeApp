package com.github.andrewmaneshin.lemonadeapp.load

import android.view.View
import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.github.andrewmaneshin.lemonadeapp.load.view.retry.RetryButton
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class RetryUi(
    @IdRes id: Int,
    containerIdMatcher: Matcher<View>?,
    containerClassTypeMatcher: Matcher<View>?
) : Visibility.Base(
    onView(
        allOf(
            withId(id),
            containerIdMatcher,
            containerClassTypeMatcher,
            isAssignableFrom(RetryButton::class.java)
        )
    )
) {

    fun click() {
        interaction.perform(androidx.test.espresso.action.ViewActions.click())
    }
}