package com.blackholecode.saudedigital.video.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.common.model.ModelContent
import com.blackholecode.saudedigital.common.model.Video
import com.blackholecode.saudedigital.common.util.SimilarItemAdapter
import com.blackholecode.saudedigital.databinding.ActivityVideoBinding
import java.util.*

class VideoActivity : AppCompatActivity() {

    companion object {
        const val VIDEO = "video"
    }

    private lateinit var binding: ActivityVideoBinding

    private var video: Video? = null

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVideoBinding.inflate(layoutInflater)

        setContentView(binding.root)

        video = intent.extras?.getParcelable(VIDEO)

        setActionBar()

//        binding.videoImgThumbnail.setImageResource(video?.thumbnail)
//        binding.videoTxtTitle.text = video?.title
//        binding.videoTxtDesc.text = video?.description

        binding.videoTxtTitle.text = video?.title
        binding.videoTxtDesc.text = video?.description

        val list = mutableListOf<ModelContent>()
        for (i in 0 until 40) {
            list.add(
                ModelContent(
                    id = UUID.randomUUID().toString(),
                    thumbnail = R.drawable.ic_insulin,
                    title = "Diabetes$i",
                    type = "diabetes"
                )
            )
        }

        val adapterRv = SimilarItemAdapter(itemClick)
        adapterRv.items = list.toMutableList()
        adapterRv.notifyDataSetChanged()

        binding.videoRecyclerSimilar.layoutManager = GridLayoutManager(this, 3)
        binding.videoRecyclerSimilar.adapter = adapterRv
    }

    private val itemClick: (ModelContent) -> Unit = { it ->

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setActionBar() {
        setSupportActionBar(binding.videoToolbar)
        supportActionBar?.apply {
            title = video?.type
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }
    }
}