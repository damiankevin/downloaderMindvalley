package com.example.mindvalleydownloader.model

import android.os.Parcel
import android.os.Parcelable
import com.example.mindvalleydownloader.model.user.ModelUserProfileImage
import com.google.gson.annotations.SerializedName

data class ModelUrl(
    @SerializedName("raw") var raw: String?,
    @SerializedName("full") var full: String?,
    @SerializedName("regular") var regular: String?,
    @SerializedName("thumb") var thumb: String?) : Parcelable {

    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString())

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(raw)
        writeString(full)
        writeString(regular)
        writeString(thumb)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ModelUrl> = object : Parcelable.Creator<ModelUrl> {
            override fun createFromParcel(source: Parcel): ModelUrl =
                ModelUrl(source)
            override fun newArray(size: Int): Array<ModelUrl?> = arrayOfNulls(size)
        }
    }
}