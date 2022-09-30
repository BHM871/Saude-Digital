package com.blackholecode.saudedigital.content.view

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.content.base.ContentBaseFragment
import com.blackholecode.saudedigital.content.util.ContentViewPageAdapter
import com.blackholecode.saudedigital.databinding.FragmentContentBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ContentFragment : ContentBaseFragment<FragmentContentBinding>(
    R.layout.fragment_content,
    FragmentContentBinding::bind
) {

//    private val homeFragment by lazy { HomeFragment() }
//    private val obesityFragment by lazy { ObesityFragment() }
//    private val hypertensionFragment by lazy { HypertensionFragment() }
//    private val diabetesFragment by lazy { DiabetesFragment() }
//    private val foodFragment by lazy { FoodFragment() }

    private lateinit var tabLayout: TabLayout
    private lateinit var viewP: ViewPager2
    private lateinit var adapter: ContentViewPageAdapter

    private lateinit var mainActivity: FragmentActivity
//    private var currentFragment: Fragment? = null
//    private var currentFragmentId: Int? = null
//    private var replace: ReplaceFragment? = null

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

//        binding?.mainBottomNav?.setOnItemSelectedListener(onSelectBottom)
//        binding?.mainBottomNav?.selectedItemId = R.id.menu_home
    }

//    private val onSelectBottom = NavigationBarView.OnItemSelectedListener { item ->
//        when (item.itemId) {
//            R.id.menu_home -> {
//                if (currentFragment == homeFragment) return@OnItemSelectedListener false
//                currentFragment = homeFragment
//            }
//            R.id.menu_obesity -> {
//                if (currentFragment == obesityFragment) return@OnItemSelectedListener false
//                currentFragment = obesityFragment
//            }
//            R.id.menu_hypertension -> {
//                if (currentFragment == hypertensionFragment) return@OnItemSelectedListener false
//                currentFragment = hypertensionFragment
//            }
//            R.id.menu_diabetes -> {
//                if (currentFragment == diabetesFragment) return@OnItemSelectedListener false
//                currentFragment = diabetesFragment
//            }
//            R.id.menu_food -> {
//                if (currentFragment == foodFragment) return@OnItemSelectedListener false
//                currentFragment = foodFragment
//            }
//        }
//
//        currentFragment?.let { replace?.replaceFragment(R.id.content_container, it) }
//        currentFragmentId?.let { binding?.mainBottomNav?.selectedItemId = it }
//
//        return@OnItemSelectedListener true
//    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

//        if (context is ReplaceFragment)
//            replace = context
    }

}