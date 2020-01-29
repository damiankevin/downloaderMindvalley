package com.example.mindvalleydownloader

import android.content.Context
import androidx.test.rule.ActivityTestRule
import com.example.downloader.RequestDownload
import com.example.mindvalleydownloader.feature.MainActivity
import org.junit.After
import org.junit.Before

import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @Rule
    @JvmField
    var mainActivityTestRule = ActivityTestRule<MainActivity>(
        MainActivity::class.java)
    var mainActivity : MainActivity? = null

    var context : Context? = null

    @Before
    fun setUp() {
        mainActivity = mainActivityTestRule.activity
    }

    @After
    fun tearDown() {
        mainActivity = null
    }

    @Test
    fun testRequestDownload(){
        context = mainActivity?.applicationContext
        RequestDownload(this.context!!).execute("http://unsplash.com/photos/H_M4dX_F1LQ/download", "H_M4dX_F1LQ")
    }
}