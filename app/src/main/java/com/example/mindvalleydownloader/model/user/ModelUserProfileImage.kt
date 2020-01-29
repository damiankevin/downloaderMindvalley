package com.example.mindvalleydownloader.model.user

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ModelUserProfileImage(
    @SerializedName("small") var small: String?,
    @SerializedName("medium") var medium: String?,
    @SerializedName("large") var large: String?) : Parcelable {

    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(small)
        writeString(medium)
        writeString(large)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ModelUserProfileImage> = object : Parcelable.Creator<ModelUserProfileImage> {
            override fun createFromParcel(source: Parcel): ModelUserProfileImage =
                ModelUserProfileImage(source)
            override fun newArray(size: Int): Array<ModelUserProfileImage?> = arrayOfNulls(size)
        }
    }
}