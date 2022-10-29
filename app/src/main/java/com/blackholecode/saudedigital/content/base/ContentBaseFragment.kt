package com.blackholecode.saudedigital.content.base

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import com.blackholecode.saudedigital.common.base.BaseFragment
import com.blackholecode.saudedigital.common.model.Food
import com.blackholecode.saudedigital.common.model.ModelContent
import com.blackholecode.saudedigital.common.model.Video
import com.blackholecode.saudedigital.content.Content
import com.blackholecode.saudedigital.content.util.ContentItemAdapter
import com.blackholecode.saudedigital.food.view.FoodActivity

abstract class ContentBaseFragment<B, P: Content.Presenter>(
    @LayoutRes layoutId: Int,
    override val bind: (View) -> B) : BaseFragment<B, P>(layoutId, bind){

    protected var isInitialized = false

    protected val adapterRv by lazy { ContentItemAdapter(itemClick) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scroll?.setScrollToolbarEnabled(true)
    }

    override fun onResume() {
        super.onResume()
            isInitialized = true
    }

    private val itemClick: (ModelContent) -> Unit = { it ->
        if (it.type == "food") {
            goToFoodScreen(it.toFood())
        } else {
            goToVideoScreen(it.toVideo())
        }
    }

    private fun goToVideoScreen(video: Video) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(video.videoUrl))
        startActivity(intent)
    }

    private fun goToFoodScreen(food: Food) {
        val intent = Intent(requireContext(), FoodActivity::class.java)

        intent.putExtra(FoodActivity.FOOD, food)

        startActivity(intent)
    }

    override fun onDestroy() {
        isInitialized = false
        super.onDestroy()
    }

}