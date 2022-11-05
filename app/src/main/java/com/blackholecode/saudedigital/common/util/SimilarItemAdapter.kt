package com.blackholecode.saudedigital.common.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.common.model.ModelContent

class SimilarItemAdapter(
    private var itemClick: (ModelContent) -> Unit
) : RecyclerView.Adapter<SimilarItemAdapter.SimilarHolder>() {

    var items: MutableList<Any> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarHolder =
        SimilarHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_similar_content, parent, false)
        )

    override fun onBindViewHolder(holder: SimilarHolder, position: Int) {
        holder.bind((items[position] as ModelContent))
    }

    override fun getItemCount(): Int = items.size

    inner class SimilarHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: ModelContent) = with(itemView) {

            val lightGreen = ContextCompat.getColorStateList(context, R.color.light_green)
            val mediumGreen = ContextCompat.getColorStateList(context, R.color.medium_green)
            val darkGreen = ContextCompat.getColorStateList(context, R.color.dark_green)

            val background = findViewById<FrameLayout>(R.id.item_similar_background)
            val imageView = findViewById<ImageView>(R.id.item_similar_img)

            if (item.condition == "obesity") {
                imageView.setImageResource(R.drawable.ic_fat)
                background.backgroundTintList =
                    lightGreen
            }
            if (item.condition == "hypertension") {
                imageView.setImageResource(R.drawable.ic_heart)
                background.backgroundTintList =
                    mediumGreen
            }
            if (item.condition == "diabetes") {
                imageView.setImageResource(R.drawable.ic_insulin)
                background.backgroundTintList =
                    darkGreen
            }

        }

    }

}