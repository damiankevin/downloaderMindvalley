package com.example.mindvalleydownloader.model.categories

import android.os.Parcel
import android.os.Parcelable
import com.example.mindvalleydownloader.model.collection.ModelLinks
import com.google.gson.annotations.SerializedName

data class ModelCategories(
    @SerializedName("id") var id: Int?,
    @SerializedName("title") var title: String?,
    @SerializedName("photo_count") var photo_count: Int?,
    @SerializedName("links") var links: ModelLinks?) : Parcelable {

    constructor(source: Parcel) : this(
        source.readInt(),
        source.readString(),
        source.readInt(),
        source.readParcelable<ModelLinks>(ModelLinks::class.java.classLoader)

    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeValue(id)
        writeString(title)
        writeValue(photo_count)
        writeParcelable(links, 0)

    }


    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ModelCategories> = object : Parcelable.Creator<ModelCategories> {
            override fun createFromParcel(source: Parcel): ModelCategories =
                ModelCategories(source)
            override fun newArray(size: Int): Array<ModelCategories?> = arrayOfNulls(size)
        }
    }
}