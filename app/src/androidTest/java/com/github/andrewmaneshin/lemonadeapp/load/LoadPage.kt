package com.github.andrewmaneshin.lemonadeapp.load

import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.github.andrewmaneshin.lemonadeapp.R

class LoadPage {

    private val containerIdMatcher = withParent(withId(R.id.loadContainer))
    private val containerClassTypeMatcher = withParent(isAssignableFrom(LinearLayout::class.java))

    private val progressUi = ProgressUi(
        id = R.id.progressBar,
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = containerClassTypeMatcher
    )
    private val errorUi = ErrorUi(
        id = R.id.errorTextView,
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = containerClassTypeMatcher
    )
    private val retryUi = RetryUi(
        id = R.id.retryButton,
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = containerClassTypeMatcher
    )

    fun assertProgressState() {
        progressUi.assertVisible()
        errorUi.assertNotVisible()
        retryUi.assertNotVisible()
    }

    fun waitTillGone() {
        progressUi.waitTillDoesNotExist()
    }

    fun waitTillError() {
        errorUi.waitTillVisible()
    }

    fun assertErrorState() {
        progressUi.assertNotVisible()
        errorUi.assertVisible()
        retryUi.assertVisible()
    }

    fun clickRetry() {
        retryUi.click()
    }
}