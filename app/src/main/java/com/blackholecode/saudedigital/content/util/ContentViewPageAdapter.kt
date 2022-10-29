package com.blackholecode.saudedigital.content.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.content.base.ContentBaseFragment
import com.blackholecode.saudedigital.content.view.*

class ContentViewPageAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    private var idPage = 0

    val tabs = arrayOf(
        R.drawable.ic_home,
        R.drawable.ic_fat,
        R.drawable.ic_heart,
        R.drawable.ic_insulin,
        R.drawable.ic_food
    )

    override fun getItemCount(): Int = tabs.size

    override fun createFragment(position: Int): Fragment {
        idPage = position
        return when(tabs[position]){
            R.drawable.ic_home -> HomeFragment()
            R.drawable.ic_fat -> ObesityFragment()
            R.drawable.ic_heart -> HypertensionFragment()
            R.drawable.ic_insulin -> DiabetesFragment()
            R.drawable.ic_food -> FoodFragment()
            else -> throw RuntimeException()
        }
    }
}