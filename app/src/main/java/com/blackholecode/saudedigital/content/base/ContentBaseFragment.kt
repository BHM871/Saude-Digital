package com.blackholecode.saudedigital.content.base

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.annotation.LayoutRes
import com.blackholecode.saudedigital.common.base.BaseFragment
import com.blackholecode.saudedigital.common.model.ModelFood
import com.blackholecode.saudedigital.common.model.ModelContent
import com.blackholecode.saudedigital.common.model.ModelVideo
import com.blackholecode.saudedigital.common.util.ModelContentAdapter
import com.blackholecode.saudedigital.content.Content
import com.blackholecode.saudedigital.food.view.FoodActivity

abstract class ContentBaseFragment<B, P: Content.Presenter>(
    @LayoutRes layoutId: Int,
    override val bind: (View) -> B) : BaseFragment<B, P>(layoutId, bind){

    protected val adapterRv by lazy { ModelContentAdapter(itemClick) }

    private val itemClick: (ModelContent) -> Unit = { it ->
        if (it.condition == "food") {
            goToFoodScreen(it.toFood())
        } else {
            goToVideoScreen(it.toVideo())
        }
    }

    private fun goToVideoScreen(modelVideo: ModelVideo) {
        var outputLink = ""
        if (!modelVideo.videoUrl?.startsWith("https://")!! &&
                !modelVideo.videoUrl?.startsWith("http://")!!) {
            outputLink = "https://"
        }
        outputLink = "$outputLink${modelVideo.videoUrl}"

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(outputLink))
        startActivity(intent)
    }

    private fun goToFoodScreen(food: ModelFood) {
        val intent = Intent(requireContext(), FoodActivity::class.java)

        intent.putExtra(FoodActivity.FOOD, food)

        startActivity(intent)
    }

    @SuppressLint("NotifyDataSetChanged")
    protected fun setupRecycler(data: List<ModelContent>) {
        adapterRv.items.clear()
        adapterRv.items.addAll(data)
        adapterRv.notifyDataSetChanged()
    }

}