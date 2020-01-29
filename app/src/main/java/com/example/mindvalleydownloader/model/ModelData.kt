package com.example.mindvalleydownloader.model

import android.os.Parcel
import android.os.Parcelable
import com.example.mindvalleydownloader.model.categories.ModelCategories
import com.example.mindvalleydownloader.model.collection.ModelLinks
import com.example.mindvalleydownloader.model.user.ModelUser
import com.google.gson.annotations.SerializedName

data class ModelData(
    @SerializedName("id") var id: String?,
    @SerializedName("created_at") var created_at: String?,
    @SerializedName("width") var width: Int?,
    @SerializedName("height") var height: Int?,
    @SerializedName("color") var color: String?,
    @SerializedName("likes") var likes: Int?,
    @SerializedName("liked_by_user") var liked_by_user: Boolean?,
    @SerializedName("user") var user: ModelUser?,
    @SerializedName("urls") var urls: ModelUrl?,
    @SerializedName("categories") var categories: ArrayList<ModelCategories>?,
    @SerializedName("links") var links: ModelLinks?) : Parcelable {

    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readInt(),
        source.readInt(),
        source.readString(),
        source.readInt(),
        source.readBoolean(),
        source.readParcelable<ModelUser>(ModelUser::class.java.classLoader),
        source.readParcelable<ModelUrl>(ModelUrl::class.java.classLoader),
        source.createTypedArrayList(ModelCategories.CREATOR),
        source.readParcelable<ModelLinks>(ModelLinks::class.java.classLoader)

    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(id)
        writeString(created_at)
        writeValue(width)
        writeValue(height)
        writeString(color)
        writeValue(likes)
        writeValue(liked_by_user)
        writeParcelable(user, 0)
        writeParcelable(urls, 0)
        writeTypedList(categories)
        writeParcelable(links, 0)

    }


    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ModelData> = object : Parcelable.Creator<ModelData> {
            override fun createFromParcel(source: Parcel): ModelData =
                ModelData(source)
            override fun newArray(size: Int): Array<ModelData?> = arrayOfNulls(size)
        }
    }
}