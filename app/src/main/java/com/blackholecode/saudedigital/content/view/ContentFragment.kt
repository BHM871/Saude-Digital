package com.blackholecode.saudedigital.content.view

import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.content.Content
import com.blackholecode.saudedigital.content.base.ContentBaseFragment
import com.blackholecode.saudedigital.content.util.ContentViewPageAdapter
import com.blackholecode.saudedigital.databinding.FragmentContentBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ContentFragment : ContentBaseFragment<FragmentContentBinding, Content.Presenter>(
    R.layout.fragment_content,
    FragmentContentBinding::bind
) {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewP: ViewPager2
    private lateinit var adapter: ContentViewPageAdapter

    private lateinit var mainActivity: FragmentActivity

    override fun setupView() {
        mainActivity = requireActivity()

        tabLayout = binding?.mainBottomNav!!
        viewP = binding?.contentContainer!!
        adapter = ContentViewPageAdapter(requireActivity())

        viewP.adapter = adapter
        viewP.currentItem = 0

        TabLayoutMediator(tabLayout, viewP) { tab, position ->
            tab.icon = ContextCompat.getDrawable(requireContext(), adapter.tabs[position])
        }.attach()
    }

    override lateinit var presenter: Content.Presenter

    override fun setupPresenter() {
    }
}