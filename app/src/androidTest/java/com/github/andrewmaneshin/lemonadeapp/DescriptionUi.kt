package com.github.andrewmaneshin.lemonadeapp

import android.view.View
import androidx.annotation.StringRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.github.andrewmaneshin.lemonadeapp.view.description.DescriptionTextView
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class DescriptionUi(id: Int, containerIdMatcher: Matcher<View>?, classTypeMatcher: Matcher<View>?) {

    private val interaction = onView(
        allOf(
            withId(id),
            containerIdMatcher,
            classTypeMatcher,
            isAssignableFrom(DescriptionTextView::class.java)
        )
    )

    fun withText(@StringRes text: Int) {
        interaction.check(matches(androidx.test.espresso.matcher.ViewMatchers.withText(text)))
    }
}