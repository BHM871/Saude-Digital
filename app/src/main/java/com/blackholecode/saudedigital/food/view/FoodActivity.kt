package com.blackholecode.saudedigital.food.view

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.common.base.DependencyInjector
import com.blackholecode.saudedigital.common.extension.toastGeneric
import com.blackholecode.saudedigital.common.model.ModelContent
import com.blackholecode.saudedigital.common.model.ModelFood
import com.blackholecode.saudedigital.common.model.ModelVideo
import com.blackholecode.saudedigital.common.util.SimilarItemAdapter
import com.blackholecode.saudedigital.databinding.ActivityFoodBinding
import com.blackholecode.saudedigital.food.Food

class FoodActivity : AppCompatActivity(), Food.View {

    companion object {
        const val FOOD = "food"
    }

    override lateinit var presenter: Food.Presenter

    private lateinit var binding: ActivityFoodBinding

    private val adapterRv by lazy { SimilarItemAdapter(itemClick) }

    private var food: ModelFood? = null

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFoodBinding.inflate(layoutInflater)

        setContentView(binding.root)

        presenter = DependencyInjector.foodPresenter(this)

        setupViews()

        binding.foodRecyclerSimilar.layoutManager = GridLayoutManager(this, 3)
        binding.foodRecyclerSimilar.adapter = adapterRv

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showProgress(enabled: Boolean) {
        binding.foodProgress.visibility = if (enabled) View.VISIBLE else View.GONE
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun displayRequestFullList(data: List<ModelContent>) {
        adapterRv.items.clear()
        adapterRv.items.addAll(data)
        adapterRv.notifyDataSetChanged()
        binding.foodRecyclerSimilar.visibility = View.VISIBLE
        binding.foodEmpty.visibility = View.GONE
    }

    override fun displayRequestEmptyList() {
        binding.foodRecyclerSimilar.visibility = View.GONE
        binding.foodEmpty.visibility = View.VISIBLE
    }

    override fun displayRequestFailure(message: String) {
        toastGeneric(this, message)
    }

    private val itemClick: (ModelContent) -> Unit = { it ->
        if (it.condition == "food") {
            setupViews(it.toFood())
        } else {
            goToVideoScreen(it.toVideo())
        }
    }

    private fun goToVideoScreen(modelVideo: ModelVideo) {
        var outputLink = ""
        if (!modelVideo.videoUrl?.startsWith("https://")!! &&
            !modelVideo.videoUrl?.startsWith("http://")!!
        ) {
            outputLink = "https://"
        }
        outputLink = "$outputLink${modelVideo.videoUrl}"

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(outputLink))
        startActivity(intent)
    }

    private fun setupViews() {
        this.food = intent.extras?.getParcelable(FOOD)

        setActionBar()

        binding.foodTxtDesc.text = food?.description

        presenter.searchSimilar(food?.type!!)
    }

    private fun setupViews(food: ModelFood) {
        this.food = food

        setActionBar()

        binding.foodTxtDesc.text = food.description

        presenter.searchSimilar(food.type!!)
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