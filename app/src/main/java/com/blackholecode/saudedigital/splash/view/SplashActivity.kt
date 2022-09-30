package com.blackholecode.saudedigital.splash.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blackholecode.saudedigital.common.extension.animationEnd
import com.blackholecode.saudedigital.databinding.ActivitySplashBinding
import com.blackholecode.saudedigital.login.view.LoginActivity
import com.blackholecode.saudedigital.main.view.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)

        setContentView(binding.root)

        fadeIn()
    }

    private fun fadeIn() {
        binding.splashImgIcon.animate().apply {
            setListener(animationEnd { fadeOut() })
            duration = 1000
            alpha(1.0f)
        }
    }

    private fun fadeOut() {
        binding.splashImgIcon.animate().apply {
            setListener(animationEnd { goToLoginScreen() })
            duration = 1000
            alpha(0.0f)
        }
    }

    private fun goToMainScreen() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    private fun goToLoginScreen() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

}