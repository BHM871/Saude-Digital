package com.blackholecode.saudedigital.splash.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blackholecode.saudedigital.common.base.DependencyInjector
import com.blackholecode.saudedigital.common.extension.animationEnd
import com.blackholecode.saudedigital.common.model.User
import com.blackholecode.saudedigital.databinding.ActivitySplashBinding
import com.blackholecode.saudedigital.login.view.LoginActivity
import com.blackholecode.saudedigital.main.view.MainActivity
import com.blackholecode.saudedigital.splash.Splash

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity(), Splash.View {

    private lateinit var binding: ActivitySplashBinding

    override lateinit var presenter: Splash.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)

        setContentView(binding.root)

        presenter = DependencyInjector.splashPresenter(this)
        fadeIn()
    }

    private fun fadeIn() {
        binding.splashImgIcon.animate().apply {
            setListener(animationEnd { presenter.log() })
            duration = 1000
            alpha(1.0f)
        }
    }

    private fun fadeOut(action: () -> Unit) {
        binding.splashImgIcon.animate().apply {
            setListener(animationEnd { action.invoke() })
            duration = 1000
            alpha(0.0f)
        }
    }

    override fun showProgress(enabled: Boolean) {
    }

    override fun goToMainScreen() {
        fadeOut {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }

    override fun goToLoginScreen() {
        fadeOut {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }

}