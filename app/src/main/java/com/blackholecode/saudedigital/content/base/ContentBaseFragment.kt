package com.blackholecode.saudedigital.content.base

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import com.blackholecode.saudedigital.common.base.BaseFragment
import com.blackholecode.saudedigital.common.model.Food
import com.blackholecode.saudedigital.common.model.Video
import com.blackholecode.saudedigital.content.util.ContentItemAdapter
import com.blackholecode.saudedigital.food.view.FoodActivity
import com.blackholecode.saudedigital.video.view.VideoActivity

abstract class ContentBaseFragment<B>(
    @LayoutRes layoutId: Int,
    override val bind: (View) -> B) : BaseFragment<B>(layoutId, bind) {

    protected val adapterRv by lazy { ContentItemAdapter(itemClick) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scroll?.setScrollToolbarEnabled(true)
    }

    private val itemClick: (Any) -> Unit = { it ->
        try {
            val item = it as Video

            goToVideoScreen(item)
        } catch (e: Exception) {
            val item = it as Food

            goToFoodScreen(item)
        }
    }

    private fun goToVideoScreen(video: Video) {
        val intent = Intent(requireContext(), VideoActivity::class.java)

        intent.putExtra(VideoActivity.VIDEO, video)

        startActivity(intent)
    }

    private fun goToFoodScreen(food: Food) {
        val intent = Intent(requireContext(), FoodActivity::class.java)

        intent.putExtra(FoodActivity.FOOD, food)

        startActivity(intent)
    }

}