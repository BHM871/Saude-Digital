package com.blackholecode.saudedigital.common.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.common.model.MContent

class SimilarItemAdapter(
    private var itemClick: (MContent) -> Unit
) : RecyclerView.Adapter<SimilarItemAdapter.SimilarHolder>() {

    var items: MutableList<Any> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarHolder =
        SimilarHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_similar_content, parent, false))

    override fun onBindViewHolder(holder: SimilarHolder, position: Int) {
        holder.bind((items[position] as MContent))
    }

    override fun getItemCount(): Int = items.size

    inner class SimilarHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: MContent) = with(itemView) {

        }

    }

}