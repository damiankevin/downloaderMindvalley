package com.example.mindvalleydownloader.controller

import com.example.mindvalleydownloader.model.ModelData
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ControllerEndpoint {
    @GET("raw/wgkJgazE")
    fun postData(): Call<ArrayList<ModelData>>

}