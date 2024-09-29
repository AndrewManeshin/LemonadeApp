package com.github.andrewmaneshin.lemonadeapp.lemonade.presentation.view.image

import android.os.Parcel
import android.os.Parcelable
import android.view.View

class ImageSavedState : View.BaseSavedState {

    private var imageRes = 0

    constructor(superState: Parcelable) : super(superState)

    private constructor(parcelIn: Parcel) : super(parcelIn) {
        imageRes = parcelIn.readInt()
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        out.writeInt(imageRes)
    }

    fun restore(): Int = imageRes

    fun save(uiState: Int) {
        imageRes = uiState
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<ImageSavedState> {
        override fun createFromParcel(parcel: Parcel): ImageSavedState =
            ImageSavedState(parcel)

        override fun newArray(size: Int): Array<ImageSavedState?> =
            arrayOfNulls(size)
    }
}