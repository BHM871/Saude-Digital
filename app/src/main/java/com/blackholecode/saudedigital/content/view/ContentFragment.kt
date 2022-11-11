package com.blackholecode.saudedigital.content.view

import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.common.base.DependencyInjector
import com.blackholecode.saudedigital.common.model.ModelContent
import com.blackholecode.saudedigital.content.Content
import com.blackholecode.saudedigital.content.base.ContentBaseFragment
import com.blackholecode.saudedigital.content.util.ContentViewPageAdapter
import com.blackholecode.saudedigital.databinding.FragmentContentBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ContentFragment : ContentBaseFragment<FragmentContentBinding, Content.Presenter>(
    R.layout.fragment_content,
    FragmentContentBinding::bind
), Content.View{

    private lateinit var tabLayout: TabLayout
    private lateinit var viewP: ViewPager2
    private lateinit var adapter: ContentViewPageAdapter

    override lateinit var presenter: Content.Presenter

    override fun setupPresenter() {
        presenter = DependencyInjector.contentPresenter(requireActivity(), this)
    }

    override fun setupView() {

        tabLayout = binding?.mainBottomNav!!
        viewP = binding?.contentContainer!!
        adapter = ContentViewPageAdapter(requireActivity())

        viewP.adapter = adapter
        viewP.currentItem = 0

        TabLayoutMediator(tabLayout, viewP) { tab, position ->
            tab.icon = ContextCompat.getDrawable(requireContext(), adapter.tabs[position])
        }.attach()



    }

    override fun getMenu(): Int {
        return R.menu.menu_search
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_videos_search -> {
                findNavController().navigate(R.id.action_nav_primary_to_nav_search)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showProgress(enabled: Boolean) {
    }

    override fun displayRequestSuccessful(data: List<ModelContent>) {
    }

    override fun displayRequestEmptyList() {
    }

    override fun displayRequestFailure(message: String) {
    }
}