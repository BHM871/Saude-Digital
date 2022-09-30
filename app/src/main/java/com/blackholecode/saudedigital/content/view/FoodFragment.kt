package com.blackholecode.saudedigital.content.view

import android.annotation.SuppressLint
import androidx.recyclerview.widget.LinearLayoutManager
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.common.model.Food
import com.blackholecode.saudedigital.content.base.ContentBaseFragment
import com.blackholecode.saudedigital.databinding.FragmentFoodBinding
import java.util.*

class FoodFragment : ContentBaseFragment<FragmentFoodBinding>(
    R.layout.fragment_food,
    FragmentFoodBinding::bind
) {

    @SuppressLint("NotifyDataSetChanged")
    override fun setupView() {
        binding?.let { binding ->
            with(binding) {
                val list = mutableListOf<Food>()
                for (i in 0 until 10) {
                    list.add(
                        Food(
                            id = UUID.randomUUID().toString(),
                            thumbnail = R.drawable.ic_food,
                            title = "Food$i",
                            description = getString(R.string.lorem)
                        ),
                    )
                }

                adapterRv.items = list.toMutableList()
                adapterRv.notifyDataSetChanged()

                foodRecyclerVideos.layoutManager = LinearLayoutManager(requireContext())
                foodRecyclerVideos.adapter = adapterRv
            }
        }
    }

}