package com.example.mindvalleydownloader.feature

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.downloader.RequestDownload
import com.example.mindvalleydownloader.R
import com.example.mindvalleydownloader.`interface`.InterfaceMainActivity
import com.example.mindvalleydownloader.adapter.AdapterData
import com.example.mindvalleydownloader.model.ModelData
import com.example.mindvalleydownloader.view.ViewMainActivity
import com.example.mindvalleydownloader.presenter.PresenterMainActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ViewMainActivity,InterfaceMainActivity,SwipeRefreshLayout.OnRefreshListener {



    private var mPresenterMainActivity: PresenterMainActivity? = null
    private lateinit var adapterData: AdapterData
    private var urlDownload : String = ""
    private var idDownload : String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mPresenterMainActivity = PresenterMainActivity(this)
        swipeRefresh.setOnRefreshListener(this)
        initRecyclerView()
        initData()
    }

    private fun initData() {
        mPresenterMainActivity?.loadData()
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(baseContext)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        adapterData = AdapterData(baseContext)
        adapterData.interfaceMainActivity = this
        rvMain?.layoutManager = layoutManager
        rvMain?.adapter = adapterData

        rvMain.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (layoutManager.findLastVisibleItemPosition() == layoutManager.itemCount - 1) {
                    mPresenterMainActivity?.loadData()
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    override fun showToast(message: String) {

    }

    override fun showData(data: ArrayList<ModelData>?) {
        for(i in 0 until data!!.size){
            adapterData.collectionData?.add(data[i])
        }
        adapterData.notifyDataSetChanged()
    }

    override fun onItemSelected(url: String, id: String) {
        urlDownload = url
        idDownload = id
        when {
            Build.VERSION.SDK_INT < Build.VERSION_CODES.M -> {
                Toast.makeText(applicationContext,"Downloading your file",Toast.LENGTH_SHORT).show()
                RequestDownload(applicationContext).execute(urlDownload,idDownload)

            }
            checkPermission() -> {
                Toast.makeText(applicationContext,"Downloading your file",Toast.LENGTH_SHORT).show()
                RequestDownload(applicationContext).execute(urlDownload,idDownload)

            }
            else -> requestPermission()
        }

    }

    fun checkPermission(): Boolean {
        val result1 = ContextCompat.checkSelfPermission(
            applicationContext,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        val result2 = ContextCompat.checkSelfPermission(
            applicationContext,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        return result1 == PackageManager.PERMISSION_GRANTED && result2 == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ),
            100
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==100){
            Toast.makeText(applicationContext,"Downloading your file",Toast.LENGTH_SHORT).show()
            RequestDownload(applicationContext).execute(urlDownload,idDownload)
        }else{

        }
    }

    override fun onRefresh() {
        swipeRefresh?.isRefreshing = false
        adapterData.collectionData?.clear()
        adapterData.notifyDataSetChanged()
        initData()
    }
}
