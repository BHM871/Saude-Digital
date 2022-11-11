package com.blackholecode.saudedigital.login.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.blackholecode.saudedigital.common.base.DependencyInjector
import com.blackholecode.saudedigital.common.extension.closeKeyboard
import com.blackholecode.saudedigital.common.extension.toastGeneric
import com.blackholecode.saudedigital.common.util.TxtWatch
import com.blackholecode.saudedigital.databinding.ActivityLoginBinding
import com.blackholecode.saudedigital.login.Login
import com.blackholecode.saudedigital.main.view.MainActivity
import com.blackholecode.saudedigital.register.view.RegisterActivity

class LoginActivity : AppCompatActivity(), Login.View {

    private lateinit var binding: ActivityLoginBinding
    override lateinit var presenter: Login.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        presenter = DependencyInjector.loginPresenter(this, this)

        with(binding) {
            loginEditEmail.addTextChangedListener(txtWatch)
            loginEditPassword.addTextChangedListener(txtWatch)

            loginEditEmail.addTextChangedListener(TxtWatch{displayEmailFailure(null)})
            loginEditPassword.addTextChangedListener(TxtWatch{displayPasswordFailure(null)})

            loginBtnEnter.setOnClickListener {
                closeKeyboard()

                presenter.login(loginEditEmail.text.toString(), loginEditPassword.text.toString())

                }

            loginBtnRegister.setOnClickListener {
                goToRegisterScreen()
            }
        }
    }

    override fun showProgress(enabled: Boolean) {
        binding.loginBtnEnter.isEnabled = !enabled
        binding.loginProgressEnter.visibility = if(enabled) View.VISIBLE else View.GONE
    }

    override fun onUserAuthenticate() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    override fun onUserUnauthorized(message: String) {
        toastGeneric(this, message)
    }

    override fun displayEmailFailure(resId: Int?) {
        resId?.let { binding.loginEditEmailInput.error = getString(it) }
    }

    override fun displayPasswordFailure(resId: Int?) {
        resId?.let { binding.loginEditPasswordInput.error = getString(it) }
    }

    override fun goToRegisterScreen() {
        val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    private val txtWatch = TxtWatch {
        binding.loginBtnEnter.isEnabled =
            binding.loginEditEmail.text.toString().isNotEmpty() &&
                    binding.loginEditPassword.text.toString().length >= 6
    }

}