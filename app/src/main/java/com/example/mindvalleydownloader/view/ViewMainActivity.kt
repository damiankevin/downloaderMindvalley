package com.example.mindvalleydownloader.view

import com.example.mindvalleydownloader.model.ModelData

interface ViewMainActivity {
    fun showToast(message: String)
    fun showData(body: ArrayList<ModelData>?)
}
