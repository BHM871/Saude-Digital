package com.blackholecode.saudedigital.main.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.databinding.ActivityMainBinding
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var appBarConf: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setSupportActionBar(binding.mainAppBarContainer.mainToolbar)

        val drawerLayout: DrawerLayout = binding.mainDrawerLayout
        val navView: NavigationView = binding.mainNavView

        appBarConf = AppBarConfiguration(
            setOf(R.id.nav_primary, R.id.nav_profile, R.id.nav_feedback, R.id.nav_about),
            drawerLayout
        )

        navController = findNavController(R.id.main_container_fragment)

        setupActionBarWithNavController(navController, appBarConf)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConf) || super.onSupportNavigateUp()
    }

    fun setScrollToolbarEnabled(enabled: Boolean = false) {
        Handler(Looper.getMainLooper()).postDelayed({
            val params =
                binding.mainAppBarContainer.mainToolbar.layoutParams as AppBarLayout.LayoutParams
            val coordinatorLayout =
                binding.mainAppBarContainer.mainAppbar.layoutParams as CoordinatorLayout.LayoutParams

            params.scrollFlags = 0
            coordinatorLayout.behavior = null

            if (enabled) {
                params.scrollFlags =
                    AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
                coordinatorLayout.behavior = AppBarLayout.Behavior()
            }

            binding.mainAppBarContainer.mainAppbar.layoutParams = coordinatorLayout
        }, 300)
    }

}