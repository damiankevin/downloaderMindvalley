package com.example.mindvalleydownloader.presenter

import android.util.Log
import com.example.mindvalleydownloader.controller.ApiMain
import com.example.mindvalleydownloader.controller.ControllerEndpoint
import com.example.mindvalleydownloader.model.ModelData
import com.example.mindvalleydownloader.view.ViewMainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PresenterMainActivity(private var mViewMainActivity: ViewMainActivity?) {

    fun loadData() {
        ApiMain().services.postData().enqueue(object :
            Callback<ArrayList<ModelData>> {
            override fun onResponse(
                call: Call<ArrayList<ModelData>>,
                response: Response<ArrayList<ModelData>>
            ) {

                if (response.code() == 200) {
                    var responseData: ArrayList<ModelData>? = ArrayList()
                    if (response.body()?.size!! > 10) {
                        for (i in 0 until 10) {
                            responseData?.add(response.body()!![i])
                        }
                        mViewMainActivity?.showData(responseData)
                    } else {
                        mViewMainActivity?.showData(response.body())
                    }

                }
            }

            override fun onFailure(call: Call<ArrayList<ModelData>>, t: Throwable) {
            }
        })
    }
}