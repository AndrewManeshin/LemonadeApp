package com.github.andrewmaneshin.lemonadeapp.load

import android.view.View
import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class ProgressUi(
    @IdRes private val id: Int,
    containerIdMatcher: Matcher<View>,
    containerClassTypeMatcher: Matcher<View>
) : Visibility.Base(
    onView(
        allOf(
            withId(id),
            containerIdMatcher,
            containerClassTypeMatcher,
            isAssignableFrom(LoadView::class.java)
        )
    )
) {

    fun waitTillDoesNotExist() {
        onView(isRoot()).perform(waitTillDoesNotExist(id, 4000))
    }
}