package com.blackholecode.saudedigital.register.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.common.extension.closeKeyboard
import com.blackholecode.saudedigital.common.extension.newOrReplaceFragment
import com.blackholecode.saudedigital.databinding.ActivityRegisterBinding
import com.blackholecode.saudedigital.login.view.LoginActivity
import com.blackholecode.saudedigital.main.view.MainActivity
import com.blackholecode.saudedigital.register.RegisterFragmentAttachListener

class RegisterActivity : AppCompatActivity(), RegisterFragmentAttachListener {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)

        setContentView(binding.root)


        newOrReplaceFragment(R.id.register_container, RegisterFragmentEmailAndPassword())
    }

    override fun replaceFragment(fragment: Fragment) {
        newOrReplaceFragment(R.id.register_container, fragment)
    }

    override fun goToMainScreen() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    override fun goToLoginScreen() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    override fun hideKeyBoard() {
        closeKeyboard()
    }

}