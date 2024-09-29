package com.github.andrewmaneshin.lemonadeapp.load.view.error

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet

class ErrorTextView : androidx.appcompat.widget.AppCompatTextView, UpdateError {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private lateinit var uiState: ErrorUiState

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            val savedState = ErrorSavedState(it)
            savedState.save(uiState)
            return savedState
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoredState = state as ErrorSavedState
        super.onRestoreInstanceState(restoredState.superState)
        update(restoredState.restore())
    }

    override fun update(uiState: ErrorUiState) {
        this.uiState = uiState
        this.uiState.update(this)
    }

    override fun updateText(textResId: Int) {
        setText(textResId)
    }

    override fun updateVisibility(visibility: Int) {
        this.visibility = visibility
    }
}