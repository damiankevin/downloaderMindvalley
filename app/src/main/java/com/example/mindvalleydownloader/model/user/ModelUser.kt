package com.example.mindvalleydownloader.model.user


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ModelUser(
    @SerializedName("id") var id: String?,
    @SerializedName("username") var username: String?,
    @SerializedName("name") var name: String?,
    @SerializedName("profile_image") var profile_image: ModelUserProfileImage?) : Parcelable {

    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readString(),
        source.readParcelable<ModelUserProfileImage>(ModelUserProfileImage::class.java.classLoader)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(id)
        writeString(username)
        writeString(name)
        writeParcelable(profile_image, 0)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ModelUser> = object : Parcelable.Creator<ModelUser> {
            override fun createFromParcel(source: Parcel): ModelUser =
                ModelUser(source)
            override fun newArray(size: Int): Array<ModelUser?> = arrayOfNulls(size)
        }
    }
}