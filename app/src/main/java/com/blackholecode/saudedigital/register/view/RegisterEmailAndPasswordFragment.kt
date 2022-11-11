package com.blackholecode.saudedigital.register.view

import android.content.Context
import android.os.Bundle
import android.view.View
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.common.base.BaseFragment
import com.blackholecode.saudedigital.common.base.DependencyInjector
import com.blackholecode.saudedigital.common.extension.toastGeneric
import com.blackholecode.saudedigital.common.util.information.view.InformationFragment
import com.blackholecode.saudedigital.common.util.TxtWatch
import com.blackholecode.saudedigital.databinding.FragmentRegisterEmailAndPasswordBinding
import com.blackholecode.saudedigital.register.RegisterFragmentAttachListener
import com.blackholecode.saudedigital.register.RegisterEmailAndPassword

class RegisterEmailAndPasswordFragment :
    BaseFragment<FragmentRegisterEmailAndPasswordBinding, RegisterEmailAndPassword.Presenter>(
        R.layout.fragment_register_email_and_password,
        FragmentRegisterEmailAndPasswordBinding::bind
    ), RegisterEmailAndPassword.View {

    override lateinit var presenter: RegisterEmailAndPassword.Presenter

    private var fragmentAttach: RegisterFragmentAttachListener? = null

    override fun setupPresenter() {
        presenter = DependencyInjector.registerEmailAndPasswordPresenter(requireActivity(), this)
    }

    override fun setupView() {
        binding?.let { binding ->
            with(binding) {
                registerEditEmail.addTextChangedListener(txtWatch)
                registerEditPassword.addTextChangedListener(txtWatch)
                registerEditConfirmPassword.addTextChangedListener(txtWatch)

                registerEditEmail.addTextChangedListener(TxtWatch { displayEmailFailure(null) })
                registerEditPassword.addTextChangedListener(TxtWatch { displayPasswordFailure(null) })
                registerEditConfirmPassword.addTextChangedListener(TxtWatch {
                    displayPasswordFailure(
                        null
                    )
                })

                registerBtnNext.setOnClickListener {
                    fragmentAttach?.hideKeyBoard()

                    presenter.create(
                        registerEditEmail.text.toString(),
                        registerEditPassword.text.toString(),
                        registerEditConfirmPassword.text.toString()
                    )

                }

                registerBtnLogin.setOnClickListener {
                    fragmentAttach?.goToLoginScreen()
                }
            }
        }
    }

    override fun showProgress(enabled: Boolean) {
        binding?.registerProgress?.visibility = if (enabled) View.VISIBLE else View.GONE
    }

    override fun displayEmailFailure(resId: Int?) {
        binding?.registerEditEmailInput?.error = resId?.let { getString(it) }
    }

    override fun displayPasswordFailure(resId: Int?) {
        binding?.registerEditPasswordInput?.error = resId?.let { getString(it) }
        binding?.registerEditConfirmPasswordInput?.error = resId?.let { getString(it) }
    }

    override fun displaySuccessCreate(email: String, password: String) {
        val fragment = InformationFragment().apply {
            arguments = Bundle().apply {
                putString(InformationFragment.EMAIL, email)
                putString(InformationFragment.PASSWORD, password)
            }
        }
        fragmentAttach?.replaceFragment(fragment)
    }

    override fun displayFailureCreate(message: String) {
        toastGeneric(requireContext(), message)
    }

    private val txtWatch = TxtWatch {
        binding?.registerBtnNext?.isEnabled =
            binding?.registerEditEmail?.text?.toString()?.isNotEmpty() ?: return@TxtWatch &&
                    (binding?.registerEditPassword?.text?.toString()?.length
                        ?: return@TxtWatch) >= 6 &&
                    (binding?.registerEditConfirmPassword?.text?.toString()?.length
                        ?: return@TxtWatch) >= 6

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is RegisterFragmentAttachListener)
            fragmentAttach = context
    }

    override fun onDestroy() {
        fragmentAttach = null
        super.onDestroy()
    }
}