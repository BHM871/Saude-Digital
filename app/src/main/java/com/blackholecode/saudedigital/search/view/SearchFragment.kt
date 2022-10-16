package com.blackholecode.saudedigital.search.view

import android.app.SearchManager
import android.content.Context
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.common.base.BaseFragment
import com.blackholecode.saudedigital.common.base.DependencyInjector
import com.blackholecode.saudedigital.databinding.FragmentSearchBinding
import com.blackholecode.saudedigital.search.Search

class SearchFragment : BaseFragment<FragmentSearchBinding, Search.Presenter> (
        R.layout.fragment_search,
        FragmentSearchBinding::bind
), Search.View {

        override lateinit var presenter: Search.Presenter

        override fun setupPresenter() {
                presenter = DependencyInjector.searchPresenter(this)
        }

        override fun setupView() {
                //TODO("Not yet implemented")
        }

        override fun getMenu(): Int {
                return R.menu.menu_search
        }

        override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
                super.onCreateOptionsMenu(menu, inflater)

                val serviceManager = requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
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

        override fun displayRequestEmptyList() {
                //TODO("Not yet implemented")
        }
}