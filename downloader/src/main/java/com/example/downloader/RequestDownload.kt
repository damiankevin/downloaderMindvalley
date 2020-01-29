package com.example.downloader

import android.app.DownloadManager
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.*
import android.util.Log
import android.webkit.WebView
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import org.json.JSONException
import org.json.JSONObject
import org.json.JSONTokener
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import androidx.core.content.ContextCompat.getSystemService




class RequestDownload(context: Context) : AsyncTask<String, String, String>() {

    var context : Context? = context


    override fun doInBackground(vararg params: String?): String {
        var flag = true
        var downloading = true
        var statusDownload : String =""

        val request = DownloadManager.Request(Uri.parse(params[0]))
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
        request.setTitle(params[1])
        request.setDescription("The file is downloading...")

        request.allowScanningByMediaScanner()
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "${System.currentTimeMillis()}")

        val manager = context?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        manager.enqueue(request)

        var query: DownloadManager.Query? = null
        query = DownloadManager.Query()
        var c: Cursor? = null
        if (query != null) {
            query.setFilterByStatus(DownloadManager.STATUS_FAILED or DownloadManager.STATUS_PAUSED or DownloadManager.STATUS_SUCCESSFUL or DownloadManager.STATUS_RUNNING or DownloadManager.STATUS_PENDING)
        } else {
            return flag.toString()
        }



        while (downloading) {
            c = manager.query(query)
            if (c!!.moveToFirst()) {
                val status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS))

                if (status == DownloadManager.STATUS_SUCCESSFUL) {
                     statusDownload = "success"
                    break
                }
                if (status == DownloadManager.STATUS_FAILED) {
                    Log.i("FLAG", "Fail")
                    statusDownload = "fail"
                    manager.enqueue(request)
                    break
                }
            }
        }

        return statusDownload
    }

}