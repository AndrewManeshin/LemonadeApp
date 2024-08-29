package com.github.andrewmaneshin.lemonadeapp.view.description

import android.content.Context
import android.util.AttributeSet

class DescriptionTextView : androidx.appcompat.widget.AppCompatTextView, UpdateTextRes {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun getFreezesText() = true

    override fun update(res: Int) {
        this.text = context.getText(res)
    }
}

interface UpdateTextRes {
    fun update(res: Int)
}