package com.blackholecode.saudedigital.content.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.common.model.Food
import com.blackholecode.saudedigital.common.model.MContent
import com.blackholecode.saudedigital.common.model.Video

class ContentItemAdapter(
    private var itemClick: (MContent) -> Unit
) : RecyclerView.Adapter<ContentItemAdapter.ContentHolder>() {

    var items: MutableList<MContent> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentHolder =
        ContentHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_content, parent, false))

    override fun onBindViewHolder(holder: ContentHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ContentHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: MContent) = with(itemView) {
            findViewById<TextView>(R.id.item_video_title).text = item.title

            item.thumbnail?.let {
                findViewById<ImageView>(R.id.item_video_thumbnail_img).setImageResource(
                    it
                )
            }

            findViewById<TextView>(R.id.item_video_desc).text = item.description

            val lightGreen = ContextCompat.getColorStateList(context, R.color.light_green)
            val mediumGreen = ContextCompat.getColorStateList(context, R.color.medium_green)
            val darkGreen =  ContextCompat.getColorStateList(context, R.color.dark_green)

            if (item.title!!.startsWith("Obesidade")) {
                findViewById<FrameLayout>(R.id.item_video_thumbnail_background).backgroundTintList = lightGreen
            }
            if (item.title!!.startsWith("Hipertensão")) {
                findViewById<FrameLayout>(R.id.item_video_thumbnail_background).backgroundTintList = mediumGreen
            }
            if (item.title!!.startsWith("Diabetes")) {
                findViewById<FrameLayout>(R.id.item_video_thumbnail_background).backgroundTintList = darkGreen
            }

            findViewById<ConstraintLayout>(R.id.item_container).setOnClickListener {
                itemClick.invoke(item)
            }
        }

    }

}