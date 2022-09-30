package com.blackholecode.saudedigital.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.blackholecode.saudedigital.common.extension.closeKeyboard
import com.blackholecode.saudedigital.common.util.TxtWatch
import com.blackholecode.saudedigital.databinding.ActivityLoginBinding
import com.blackholecode.saudedigital.main.view.MainActivity
import com.blackholecode.saudedigital.register.view.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)
        with(binding) {
            loginEditEmail.addTextChangedListener(txtWatch)
            loginEditPassword.addTextChangedListener(txtWatch)

            loginEditEmail.addTextChangedListener(TxtWatch{displayInputFailure()})
            loginEditPassword.addTextChangedListener(TxtWatch{displayInputFailure()})

            loginBtnEnter.setOnClickListener {
                closeKeyboard()

                loginBtnEnter.isEnabled = false

                loginProgressEnter.visibility = View.VISIBLE
                Handler(Looper.getMainLooper()).postDelayed({
                    loginProgressEnter.visibility = View.GONE
                }, 1000)

            }

            loginBtnRegister.setOnClickListener {
                val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }
        }
    }

    private val txtWatch = TxtWatch {
        binding.loginBtnEnter.isEnabled =
            binding.loginEditEmail.text.toString().isNotEmpty() &&
                    binding.loginEditPassword.text.toString().length >= 6

    }

    private fun displayInputFailure() {
        binding.loginEditEmailInput.error = "Error"
        binding.loginEditPasswordInput.error = "Error"
    }

}