package com.github.andrewmaneshin.lemonadeapp

import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class DescriptionUi(id: Int, containerIdMatcher: Matcher<View>?, classTypeMatcher: Matcher<View>?) {

    private val interaction = onView(
        allOf(
            withId(id),
            containerIdMatcher,
            classTypeMatcher,
            isAssignableFrom(TextView::class.java)
        )
    )

    fun withText(text: String) {
        interaction.check(matches(androidx.test.espresso.matcher.ViewMatchers.withText(text)))
    }
}