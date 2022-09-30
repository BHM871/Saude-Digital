package com.blackholecode.saudedigital.register.view

import  android.os.Handler
import android.os.Looper
import android.view.View
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.common.util.FragmentInformation
import com.blackholecode.saudedigital.common.util.TxtWatch
import com.blackholecode.saudedigital.databinding.FragmentRegisterEmailAndPasswordBinding
import com.blackholecode.saudedigital.register.base.RegisterBaseFragment

class FragmentRegisterEmailAndPassword :
    RegisterBaseFragment<FragmentRegisterEmailAndPasswordBinding>(
        R.layout.fragment_register_email_and_password,
        FragmentRegisterEmailAndPasswordBinding::bind
    ) {

    override fun setupView() {
        binding?.let { binding ->
            with(binding) {
                registerEditEmail.addTextChangedListener(txtWatch)
                registerEditPassword.addTextChangedListener(txtWatch)
                registerEditConfirmPassword.addTextChangedListener(txtWatch)

                registerEditEmail.addTextChangedListener(TxtWatch { displayInputFailure() })
                registerEditPassword.addTextChangedListener(TxtWatch { displayInputFailure() })
                registerEditConfirmPassword.addTextChangedListener(TxtWatch { displayInputFailure() })

                registerBtnNext.setOnClickListener {
                    fragmentAttach?.hideKeyBoard()
                    registerBtnNext.isEnabled = false
                    registerProgressNext.visibility = View.VISIBLE

                    Handler(Looper.getMainLooper()).postDelayed({
                        registerProgressNext.visibility = View.GONE
                        fragmentAttach?.replaceFragment(FragmentInformation())
                    }, 1000)
                }

                registerBtnLogin.setOnClickListener {
                    fragmentAttach?.goToLoginScreen()
                }
            }
        }
    }

    private val txtWatch = TxtWatch {
        binding?.registerBtnNext?.isEnabled =
            binding?.registerEditEmail?.text?.toString()?.isNotEmpty() ?: return@TxtWatch &&
                    (binding?.registerEditPassword?.text?.toString()?.length ?: return@TxtWatch) >= 6 &&
                    (binding?.registerEditConfirmPassword?.text?.toString()?.length ?: return@TxtWatch) >= 6

    }

    private fun displayInputFailure() {
        binding?.registerEditEmailInput?.error = "Error"
        binding?.registerEditPasswordInput?.error = "Error"
        binding?.registerEditConfirmPasswordInput?.error = "Error"
    }
}