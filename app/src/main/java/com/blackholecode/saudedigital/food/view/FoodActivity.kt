package com.blackholecode.saudedigital.food.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.common.model.Food
import com.blackholecode.saudedigital.common.model.MContent
import com.blackholecode.saudedigital.common.model.Video
import com.blackholecode.saudedigital.common.util.SimilarItemAdapter
import com.blackholecode.saudedigital.databinding.ActivityFoodBinding
import java.util.*

class FoodActivity : AppCompatActivity() {

    companion object {
        const val FOOD = "food"
    }

    private lateinit var binding: ActivityFoodBinding

    private var food: Food? = null

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFoodBinding.inflate(layoutInflater)

        setContentView(binding.root)

        food = intent.extras?.getParcelable(FOOD)

        setActionBar()

        binding.foodTxtDesc.text = food?.description
//        binding.foodImgThumbnail.setImageResource(food?.thumbnail)


        val list = mutableListOf<MContent>()
        for (i in 0 until 40) {
            list.add(
                MContent(
                    id = UUID.randomUUID().toString(),
                    thumbnail = R.drawable.ic_insulin,
                    title = "Diabetes$i",
                    type = "Diabetes"
                )
            )
        }

        val adapterRv = SimilarItemAdapter(itemClick)
        adapterRv.items = list.toMutableList()
        adapterRv.notifyDataSetChanged()

        binding.foodRecyclerSimilar.layoutManager = GridLayoutManager(this, 3)
        binding.foodRecyclerSimilar.adapter = adapterRv

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private val itemClick: (Any) -> Unit = { it ->

    }

    private fun setActionBar() {
        setSupportActionBar(binding.foodToolbar)
        supportActionBar?.apply {
            title = food?.title
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }
    }
}