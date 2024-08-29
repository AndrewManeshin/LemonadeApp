package com.github.andrewmaneshin.lemonadeapp.view.image

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import kotlin.properties.Delegates

class ImageSavedState : View.BaseSavedState {

    private var imageRes by Delegates.notNull<Int>()

    constructor(superState: Parcelable) : super(superState)

    private constructor(parcelIn: Parcel) : super(parcelIn) {
        imageRes = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            parcelIn.readSerializable(Int::class.java.classLoader, Int::class.java) as Int
        } else {
            parcelIn.readSerializable() as Int
        }
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        out.writeSerializable(imageRes)
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