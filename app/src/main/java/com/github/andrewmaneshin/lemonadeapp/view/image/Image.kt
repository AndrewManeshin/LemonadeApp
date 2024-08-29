package com.github.andrewmaneshin.lemonadeapp.view.image

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import kotlin.properties.Delegates

class Image : androidx.appcompat.widget.AppCompatImageButton, UpdateImageRes {

    private var imageRes by Delegates.notNull<Int>()

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun update(res: Int) {
        imageRes = res
        setImageResource(res)
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

interface UpdateImageRes {
    fun update(res: Int)
}