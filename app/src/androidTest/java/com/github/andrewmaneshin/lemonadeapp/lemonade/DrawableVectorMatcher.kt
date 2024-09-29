package com.github.andrewmaneshin.lemonadeapp.lemonade

import android.view.View
import android.widget.ImageButton
import androidx.annotation.DrawableRes
import androidx.core.graphics.drawable.toBitmap
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description

class DrawableVectorMatcher(@DrawableRes private val expectingDrawableResID: Int) :
    BoundedMatcher<View, ImageButton>(ImageButton::class.java) {

    override fun describeTo(description: Description) {
        description.appendText("with drawable from resource id: ")
        description.appendValue(expectingDrawableResID)
    }

    override fun matchesSafely(item: ImageButton): Boolean {
        val expectedDrawable = item.context.getDrawable(expectingDrawableResID) ?: return false
        val actualDrawable = item.drawable ?: return false
        return actualDrawable.toBitmap().sameAs(expectedDrawable.toBitmap())
    }
}