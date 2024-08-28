package com.github.andrewmaneshin.lemonadeapp

import android.graphics.Bitmap
import android.view.View
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description

class DrawableVectorMatcher(private val expectingDrawableResID: Int) :
    BoundedMatcher<View, ImageButton>(ImageButton::class.java) {

    override fun describeTo(description: Description) {
        description.appendText("with drawable from resource id: ")
        description.appendValue(expectingDrawableResID)
    }

    override fun matchesSafely(item: ImageButton): Boolean {
        if (expectingDrawableResID < 0) {
            return item.drawable == null
        }
        val expectedDrawable = ContextCompat.getDrawable(item.context, expectingDrawableResID)
            ?: return false

        val bitmap = Bitmap.createBitmap(
            item.drawable.intrinsicWidth,
            item.drawable.intrinsicHeight, Bitmap.Config.ARGB_8888
        )
        val newExpectedDrawable = Bitmap.createBitmap(
            expectedDrawable.intrinsicWidth,
            expectedDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888
        )
        return bitmap.sameAs(newExpectedDrawable)
    }
}