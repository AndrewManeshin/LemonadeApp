package com.github.andrewmaneshin.lemonadeapp.load

import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import org.hamcrest.Matchers.not

interface Visibility {

    fun assertNotVisible()

    fun assertVisible()

    abstract class Base(protected val interaction: ViewInteraction) : Visibility {

        override fun assertNotVisible() {
            interaction.check(matches(not(isDisplayed())))
        }

        override fun assertVisible() {
            interaction.check(matches(isDisplayed()))
        }
    }
}