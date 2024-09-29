package com.github.andrewmaneshin.lemonadeapp.lemonade.presentation.view.image

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet

class LemonadeImageButton : androidx.appcompat.widget.AppCompatImageButton, UpdateImageButton {

    private var imageRes = 0

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun update(res: Int) {
        imageRes = res
        setImageResource(imageRes)
    }

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            val savedState = ImageSavedState(it)
            savedState.save(imageRes)
            return savedState
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoredState = state as ImageSavedState
        super.onRestoreInstanceState(restoredState.superState)
        update(restoredState.restore())
    }
}

interface UpdateImageButton {
    fun update(res: Int)
}