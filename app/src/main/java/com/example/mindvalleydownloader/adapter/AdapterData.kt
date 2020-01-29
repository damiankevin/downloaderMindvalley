package com.example.mindvalleydownloader.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mindvalleydownloader.`interface`.InterfaceMainActivity
import com.example.mindvalleydownloader.holder.HolderData
import com.example.mindvalleydownloader.model.ModelData

class AdapterData(context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val ITEM_TYPE_CONTENT: Int = 1
    }

    var collectionData: ArrayList<ModelData>? = ArrayList()
    var interfaceMainActivity : InterfaceMainActivity? = null

    private var itemContext: Context? = context
    var context: Context? = context

    override fun getItemViewType(position: Int): Int {
        return ITEM_TYPE_CONTENT
    }

    override fun getItemCount(): Int {
        return collectionData?.size!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(itemContext)
        val itemViewThumbnail = layoutInflater.inflate(HolderData.LAYOUT_RESOURCE, parent, false)
        return HolderData(itemViewThumbnail, itemContext,interfaceMainActivity)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            ITEM_TYPE_CONTENT -> {
                (holder as? HolderData)?.bindingContent(collectionData?.get(position)!!)
            }
        }
    }


}