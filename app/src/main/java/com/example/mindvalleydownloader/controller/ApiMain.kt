package com.example.mindvalleydownloader.controller

import android.app.Application
import com.example.mindvalleydownloader.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiMain : Application() {
    private val client = OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        })
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pastebin.com/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val services: ControllerEndpoint = retrofit.create(ControllerEndpoint::class.java)
}