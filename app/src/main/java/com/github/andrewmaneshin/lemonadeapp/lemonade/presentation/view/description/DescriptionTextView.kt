package com.github.andrewmaneshin.lemonadeapp.lemonade.presentation.view.description

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.StringRes

class DescriptionTextView : androidx.appcompat.widget.AppCompatTextView, UpdateTextRes {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun getFreezesText() = true

    override fun update(@StringRes res: Int) {
        this.setText(res)
    }
}

interface UpdateTextRes {
    fun update(@StringRes res: Int)
}