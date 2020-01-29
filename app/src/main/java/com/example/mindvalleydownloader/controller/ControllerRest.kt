package com.example.mindvalleydownloader.controller

import android.app.Application
import android.util.JsonReader
import android.util.JsonToken
import android.util.JsonWriter
import com.example.mindvalleydownloader.BuildConfig
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapter
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class ControllerRest {

    fun newInstance(application: Application): ControllerEndpoint {
        val httpclient = OkHttpClient.Builder()

        val okHttpClient = httpclient.readTimeout(60, TimeUnit.SECONDS).connectTimeout(60, TimeUnit.SECONDS).build()


        val gsonBuilder = GsonBuilder().setLenient().registerTypeAdapter(Integer::class.java, IntegerTypeAdapter())   .registerTypeAdapter(String::class.java, StringTypeAdapter())
        gsonBuilder.serializeNulls()
        val client = OkHttpClient.Builder()

        if(BuildConfig.DEBUG) client.addInterceptor(logInterceptor)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pastebin.com/")
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .client(client.build())
            .build()
        return retrofit.create(ControllerEndpoint::class.java)
    }


    private val logInterceptor: HttpLoggingInterceptor
        get() {
            val httpLoggingInterceptor      = HttpLoggingInterceptor()
            httpLoggingInterceptor.level    = HttpLoggingInterceptor.Level.BODY
            return httpLoggingInterceptor
        }

    class IntegerTypeAdapter : TypeAdapter<Int>() {
        override fun read(reader: com.google.gson.stream.JsonReader?): Int {
            if (reader?.peek() == JsonToken.NULL) {
                reader.nextNull()
                return 0
            }
            val stringValue = reader?.nextString()
            return try {
                Integer.valueOf(stringValue!!)
            } catch (e: NumberFormatException) {
                0
            }
        }

        override fun write(writer: com.google.gson.stream.JsonWriter?, value: Int?) {
            if (value == null) {
                writer?.nullValue()
                return
            }
            writer?.value(value)
        }


    }

    class StringTypeAdapter : TypeAdapter<String>() {
        override fun read(reader: com.google.gson.stream.JsonReader?): String {
            if (reader?.peek() == JsonToken.NULL) {
                reader.nextNull()
                return ""
            }
            val stringValue = reader?.nextString()
            try {
                return stringValue.toString()
            } catch (e: Exception) {
                return ""
            }
        }

        override fun write(writer: com.google.gson.stream.JsonWriter?, value: String?) {
            if (value == null) {
                writer?.nullValue()
                return
            }
            writer?.value(value)
        }

    }

}
