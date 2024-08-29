package com.github.andrewmaneshin.lemonadeapp.view.image

import android.content.Context
import android.util.AttributeSet

class Image : androidx.appcompat.widget.AppCompatImageButton, UpdateImageRes {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun update(res: Int) {
        setImageResource(res)
    }

    //     override fun onSaveInstanceState(): Parcelable? {
//        return super.onSaveInstanceState()?.let {
//            val savedState = (it)
//            savedState.save(state)
//            return savedState
//        }
//    }
//
//    override fun onRestoreInstanceState(state: Parcelable?) {
//        val restoredState = state as
//        super.onRestoreInstanceState(restoredState.superState)
//        update(restoredState.restore())
//    }
}

interface UpdateImageRes {
    fun update(res: Int)
}