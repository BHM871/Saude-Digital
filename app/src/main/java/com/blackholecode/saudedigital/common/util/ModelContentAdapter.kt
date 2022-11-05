package com.blackholecode.saudedigital.common.util

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
import com.blackholecode.saudedigital.common.model.ModelContent

class ModelContentAdapter(
    private var itemClick: (ModelContent) -> Unit
) : RecyclerView.Adapter<ModelContentAdapter.ContentHolder>() {

    var items: MutableList<ModelContent> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentHolder =
        ContentHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_content, parent, false))

    override fun onBindViewHolder(holder: ContentHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ContentHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: ModelContent) = with(itemView) {
            findViewById<TextView>(R.id.item_title).text = item.title

            findViewById<TextView>(R.id.item_video_desc).text = item.description

            val lightGreen = ContextCompat.getColorStateList(context, R.color.light_green)
            val mediumGreen = ContextCompat.getColorStateList(context, R.color.medium_green)
            val darkGreen =  ContextCompat.getColorStateList(context, R.color.dark_green)

            val background = findViewById<FrameLayout>(R.id.item_thumbnail_background)
            val imageView = findViewById<ImageView>(R.id.item_thumbnail_img)

            when (item.condition) {
                "obesity" -> {
                    imageView.setImageResource(R.drawable.ic_fat)
                    background.backgroundTintList =
                        lightGreen
                }
                "hypertension" -> {
                    imageView.setImageResource(R.drawable.ic_heart)
                    background.backgroundTintList =
                        mediumGreen
                }
                "diabetes" -> {
                    imageView.setImageResource(R.drawable.ic_insulin)
                    background.backgroundTintList =
                        darkGreen
                }
            }

            findViewById<ConstraintLayout>(R.id.item_container).setOnClickListener {
                itemClick.invoke(item)
            }
        }

    }

}