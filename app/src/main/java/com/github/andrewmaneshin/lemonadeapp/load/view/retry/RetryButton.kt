package com.github.andrewmaneshin.lemonadeapp.load.view.retry

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import com.github.andrewmaneshin.lemonadeapp.load.view.visibility.UpdateVisibility
import com.github.andrewmaneshin.lemonadeapp.load.view.visibility.VisibilitySavedState
import com.github.andrewmaneshin.lemonadeapp.load.view.visibility.VisibilityUiState

class RetryButton : androidx.appcompat.widget.AppCompatButton, UpdateVisibility {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private lateinit var uiState: VisibilityUiState

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            val savedState = VisibilitySavedState(it)
            savedState.save(uiState)
            return savedState
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoredState = state as VisibilitySavedState
        super.onRestoreInstanceState(restoredState.superState)
        update(restoredState.restore())
    }

    override fun update(state: VisibilityUiState) {
        uiState = state
        state.update(this)
    }

    override fun update(visibility: Int) {
        this.visibility = visibility
    }
}