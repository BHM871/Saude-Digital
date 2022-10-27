package com.blackholecode.saudedigital.register.view

import android.content.Context
import android.view.View
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.common.base.BaseFragment
import com.blackholecode.saudedigital.common.base.DependencyInjector
import com.blackholecode.saudedigital.common.extension.toastGeneric
import com.blackholecode.saudedigital.common.view.CustomDialog
import com.blackholecode.saudedigital.databinding.FragmentRegisterPhotoBinding
import com.blackholecode.saudedigital.register.RegisterFragmentAttachListener
import com.blackholecode.saudedigital.register.RegisterPhoto

class RegisterPhotoFragment :
    BaseFragment<FragmentRegisterPhotoBinding, RegisterPhoto.Presenter>(
        R.layout.fragment_register_photo,
        FragmentRegisterPhotoBinding::bind
    ), RegisterPhoto.View {

    companion object{
        val PHOTO = "photo"
    }

    override lateinit var presenter: RegisterPhoto.Presenter

    private var fragmentAttach: RegisterFragmentAttachListener? = null

    private var name: String? = null

    override fun setupPresenter() {
        presenter = DependencyInjector.registerPhotoPresenter(this)
    }

    override fun setupView() {

        name = arguments?.getString(PHOTO)

        name?.let { binding?.photoTxtWelcome?.text = getString(R.string.welcome_saude_digital, name) }

        binding?.let { binding ->
            with(binding) {

                photoBtnAdd.setOnClickListener {
                    val dialog = CustomDialog(requireContext())
                    dialog.setTitle(R.string.camOrGall)
                    dialog.addOption(R.string.camera, R.string.gallery){
                        when(id) {
                            R.string.camera -> {

                            }
                            R.string.gallery -> {

                            }
                        }
                    }
                }

                photoBtnJump.setOnClickListener {
                    fragmentAttach?.goToMainScreen()
                }
            }
        }
    }

    override fun showProgress(enabled: Boolean) {
        binding?.photoProgress?.visibility = if (enabled) View.VISIBLE else View.GONE
    }

    override fun displayUpdateSuccess() {
        fragmentAttach?.goToMainScreen()
    }

    override fun displayUpdateFailure(message: String) {
        toastGeneric(requireContext(), message)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is RegisterFragmentAttachListener)
            fragmentAttach = context
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}