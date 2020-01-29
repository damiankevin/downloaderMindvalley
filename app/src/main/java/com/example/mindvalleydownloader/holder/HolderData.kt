package com.example.mindvalleydownloader.holder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mindvalleydownloader.`interface`.InterfaceMainActivity
import com.example.mindvalleydownloader.model.ModelData
import kotlinx.android.synthetic.main.holder_data.view.*
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import android.widget.FrameLayout
import android.R
import kotlinx.android.synthetic.main.fab_layout.view.*
import android.view.animation.AnimationUtils
import android.R.drawable
import android.graphics.drawable.Drawable
import android.util.Log
import com.example.mindvalleydownloader.*


class HolderData(
    itemView: View?,
    context: Context?,
    interfaceMainActivity: InterfaceMainActivity?
) : RecyclerView.ViewHolder(itemView!!) {

    companion object {
        const val LAYOUT_RESOURCE: Int = com.example.mindvalleydownloader.R.layout.holder_data
    }

    private var itemContext = context
    private var interfaceMainActivity = interfaceMainActivity
    private var showFab: Boolean = false

    fun bindingContent(content: ModelData) {


        Glide.with(this.itemContext!!)
            .load(content.urls?.regular)
            .into(itemView.ivImage)
        var glide = Glide.with(this.itemContext!!)
            .load(content.user?.profile_image?.small)
            .into(itemView.civAvatar)


        itemView.tvName.text = content.user?.name + " - " + content.user?.username

        itemView.rlHolderCancelLoad.setOnClickListener {
            itemView.rlHolderCancelLoad.visibility = View.GONE
            glide.clearOnDetach()
        }


        itemView.rlHolderContent.setOnClickListener {
            //            interfaceMainActivity?.onItemSelected(content.links?.download.toString(), content.id!!)

            clickFAB(content)
        }


    }

    private fun clickFAB(content: ModelData) {
        val show_fab_1 = AnimationUtils.loadAnimation(
            itemContext,
            com.example.mindvalleydownloader.R.anim.fab1_show
        )
        val hide_fab_1 = AnimationUtils.loadAnimation(
            itemContext,
            com.example.mindvalleydownloader.R.anim.fab1_hide
        )
        if (showFab) {
            Log.d("clickholder", "a")
            itemView.llHolderFAB.visibility = View.GONE
            showFab = false
            itemView.rlHolderContent.alpha = 1f

            itemView.fabCancel.startAnimation(hide_fab_1)
            itemView.fabCancel.isClickable = false
            itemView.fabDownload.startAnimation(hide_fab_1)
            itemView.fabDownload.isClickable = false

        } else {
            itemView.llHolderFAB.visibility = View.VISIBLE
            itemView.rlHolderContent.alpha = 0.3f
            showFab = true
            itemView.fabCancel.startAnimation(show_fab_1)
            itemView.fabCancel.isClickable = true
            itemView.fabDownload.startAnimation(show_fab_1)
            itemView.fabDownload.isClickable = true
            itemView.fabCancel.setOnClickListener {
                clickFAB(content)
            }
            itemView.fabDownload.setOnClickListener {
                interfaceMainActivity?.onItemSelected(content.links?.download!!, content.id!!)

            }
        }
    }

}
