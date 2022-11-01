package com.blackholecode.saudedigital.search.view

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.common.base.BaseFragment
import com.blackholecode.saudedigital.common.base.DependencyInjector
import com.blackholecode.saudedigital.common.extension.toastGeneric
import com.blackholecode.saudedigital.common.model.Food
import com.blackholecode.saudedigital.common.model.ModelContent
import com.blackholecode.saudedigital.common.model.Video
import com.blackholecode.saudedigital.common.util.ModelContentAdapter
import com.blackholecode.saudedigital.databinding.FragmentSearchBinding
import com.blackholecode.saudedigital.food.view.FoodActivity
import com.blackholecode.saudedigital.search.Search

class SearchFragment : BaseFragment<FragmentSearchBinding, Search.Presenter>(
    R.layout.fragment_search,
    FragmentSearchBinding::bind
), Search.View {

    override lateinit var presenter: Search.Presenter

    private val adapterRv by lazy { ModelContentAdapter(itemClick) }

    override fun setupPresenter() {
        presenter = DependencyInjector.searchPresenter(this)
    }

    override fun setupView() {
        binding?.searchRecycler?.layoutManager = LinearLayoutManager(requireContext())
        binding?.searchRecycler?.adapter = adapterRv
    }

    override fun getMenu(): Int {
        return R.menu.menu_search
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val serviceManager =
            requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.menu_videos_search).actionView as SearchView

        searchView.apply {
            setSearchableInfo(serviceManager.getSearchableInfo(requireActivity().componentName))
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText != null) {
                        presenter.searchVideos(newText)
                    }
                    return false
                }
            })
        }
    }

    override fun showProgress(enabled: Boolean) {
        binding?.searchProgress?.visibility = if (enabled) View.VISIBLE else View.GONE
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun displayRequestFullList(data: List<ModelContent>) {
        adapterRv.items.clear()
        adapterRv.items.addAll(data)
        adapterRv.notifyDataSetChanged()
        binding?.searchRecycler?.visibility = View.VISIBLE
        binding?.searchEmpty?.visibility = View.GONE
    }

    override fun displayRequestEmptyList() {
        binding?.searchRecycler?.visibility = View.GONE
        binding?.searchEmpty?.visibility = View.VISIBLE
    }

    override fun displayRequestFailure(message: String) {
        toastGeneric(requireContext(), message)
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

}