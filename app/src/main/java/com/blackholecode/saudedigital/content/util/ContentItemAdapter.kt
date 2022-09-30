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
import com.blackholecode.saudedigital.common.model.Video
import com.google.android.material.imageview.ShapeableImageView

class ContentItemAdapter(
    private var itemClick: (Any) -> Unit
) : RecyclerView.Adapter<ContentItemAdapter.ContentHolder>() {

    private val viewTypeO = 0
    private val viewTypeH = 1
    private val viewTypeD = 2
    private val viewTypeF = 3

    var items: MutableList<Any> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentHolder =
        ContentHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_content, parent, false))

    override fun onBindViewHolder(holder: ContentHolder, position: Int) {
        try {
            holder.bind(items[position] as Food)
        } catch (e: Exception) {
            holder.bind(items[position] as Video)
        }
    }

    override fun getItemCount(): Int = items.size

    inner class ContentHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Video) = with(itemView) {
            findViewById<TextView>(R.id.item_video_title).text = item.title

            findViewById<ImageView>(R.id.item_video_thumbnail_img).setImageResource(item.thumbnail)

            val lightGreen = ContextCompat.getColorStateList(context, R.color.light_green)
            val mediumGreen = ContextCompat.getColorStateList(context, R.color.medium_green)
            val darkGreen =  ContextCompat.getColorStateList(context, R.color.dark_green)

            if (item.title.startsWith("Obesidade")) {
                findViewById<FrameLayout>(R.id.item_video_thumbnail_background).backgroundTintList = lightGreen
            }
            if (item.title.startsWith("Hipertensão")) {
                findViewById<FrameLayout>(R.id.item_video_thumbnail_background).backgroundTintList = mediumGreen
            }
            if (item.title.startsWith("Diabetes")) {
                findViewById<FrameLayout>(R.id.item_video_thumbnail_background).backgroundTintList = darkGreen
            }

//            findViewById<TextView>(R.id.item_video_desc).text = item.description

            findViewById<ConstraintLayout>(R.id.item_container).setOnClickListener {
                itemClick.invoke(item)
            }
        }

        fun bind(item: Food) = with(itemView) {
            findViewById<TextView>(R.id.item_video_title).text = item.title

            findViewById<ImageView>(R.id.item_video_thumbnail_img).setImageResource(item.thumbnail)

//            findViewById<TextView>(R.id.item_video_desc).text = item.description

            findViewById<ConstraintLayout>(R.id.item_container).setOnClickListener {
                itemClick.invoke(item)
            }
        }

    }

}