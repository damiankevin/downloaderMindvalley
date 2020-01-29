package com.example.mindvalleydownloader.model.collection

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ModelLinks(
    @SerializedName("self") var self: String?,
    @SerializedName("photos") var photos: String?,
    @SerializedName("html") var html: String?,
    @SerializedName("download") var download: String?) : Parcelable {

    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString()


    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(self)
        writeValue(photos)
        writeString(html)
        writeString(download)


    }


    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ModelLinks> = object : Parcelable.Creator<ModelLinks> {
            override fun createFromParcel(source: Parcel): ModelLinks =
                ModelLinks(source)
            override fun newArray(size: Int): Array<ModelLinks?> = arrayOfNulls(size)
        }
    }
}