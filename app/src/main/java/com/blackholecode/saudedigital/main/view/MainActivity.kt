package com.blackholecode.saudedigital.main.view

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.common.base.DependencyInjector
import com.blackholecode.saudedigital.common.extension.closeKeyboard
import com.blackholecode.saudedigital.common.util.NotificationPublisher
import com.blackholecode.saudedigital.databinding.ActivityMainBinding
import com.blackholecode.saudedigital.main.Main
import com.blackholecode.saudedigital.main.MainFragmentAttachListener
import com.blackholecode.saudedigital.splash.view.SplashActivity
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), Main.View, MainFragmentAttachListener {

    private lateinit var binding: ActivityMainBinding

    override lateinit var presenter: Main.Presenter

    private lateinit var appBarConf: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("test", "onCreate: ")

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        presenter = DependencyInjector.mainPresenter(this)

        setSupportActionBar(binding.mainAppbarContainer.mainToolbar)

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

    override fun showProgress(enabled: Boolean) {
        binding.mainProgress.visibility = if (enabled) View.VISIBLE else View.GONE
    }

    override fun hideKeyBoard() {
        closeKeyboard()
    }

    override fun logout() {
        presenter.logout()
    }

    override fun displayLogoutSuccess() {
        startActivity(Intent(this, SplashActivity::class.java))
        finish()
    }

    @SuppressLint("ShortAlarm", "UnspecifiedImmutableFlag")
    private fun notification() {
        val intent = Intent(this, NotificationPublisher::class.java)
        intent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1001)

        val broadcast = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )
        } else {
            PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_CANCEL_CURRENT
            )
        }

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            (System.currentTimeMillis() + 1000),
            216_000_000,
            broadcast
        )
    }

    override fun onStart() {
        super.onStart()
        Log.d("test", "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d("test", "onResume: ")
    }

    override fun onStop() {
        Log.d("test", "onStop: ")
        super.onStop()
    }

    override fun onPause() {
        Log.d("test", "onPause: ")
        notification()
        super.onPause()
    }

    override fun onDestroy() {
        Log.d("test", "onDestroy: ")
        presenter.onDestroy()

        super.onDestroy()
    }

}