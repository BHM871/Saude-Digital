package com.blackholecode.saudedigital.common.view.information.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.common.base.AttachListener
import com.blackholecode.saudedigital.common.base.BaseFragment
import com.blackholecode.saudedigital.common.base.DependencyInjector
import com.blackholecode.saudedigital.common.extension.toastGeneric
import com.blackholecode.saudedigital.common.model.User
import com.blackholecode.saudedigital.common.util.Condition
import com.blackholecode.saudedigital.common.view.ImcActivity
import com.blackholecode.saudedigital.common.view.information.Information
import com.blackholecode.saudedigital.databinding.FragmentInformationBinding
import com.blackholecode.saudedigital.register.RegisterFragmentAttachListener
import com.blackholecode.saudedigital.register.view.RegisterActivity
import com.blackholecode.saudedigital.register.view.RegisterPhotoFragment
import com.google.android.material.textfield.TextInputLayout

class InformationFragment : BaseFragment<FragmentInformationBinding, Information.Presenter>(
    R.layout.fragment_information,
    FragmentInformationBinding::bind
), Information.View {

    companion object {
        const val EMAIL = "email"
        const val PASSWORD = "password"

        var imc: String? = null
    }

    override lateinit var presenter: Information.Presenter

    private var isRegister: Boolean = false

    private var fragmentAttachRegister: RegisterFragmentAttachListener? = null
    private var fragmentAttach: AttachListener? = null

    private var email: String? = null
    private var password: String? = null
    private var imc: String? = null

    private lateinit var itemsDisease: Array<String>
    private lateinit var itemsTypeDisease: Array<String>

    override fun setupPresenter() {
        presenter = DependencyInjector.informationPresenter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (activity?.javaClass?.simpleName == RegisterActivity().javaClass.simpleName) {
            isRegister = true
            fragmentAttachRegister = fragmentAttach as RegisterFragmentAttachListener
        } else {
            binding?.informationBtnAction?.text = getString(R.string.finish_edit)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    @SuppressLint("ResourceType", "NewApi")
    override fun setupView() {
        itemsDisease = resources.getStringArray(R.array.disease)
        itemsTypeDisease = resources.getStringArray(R.array.type_disease)

        email = arguments?.getString(EMAIL)
        password = arguments?.getString(PASSWORD)

        if (!isRegister) {
            binding?.informationContainerLogin?.visibility = View.GONE
            presenter.fetchUser()
        }

        binding?.let { binding ->
            with(binding) {
                setAdapterAutoComplete(informationAutoCompleteDisease, itemsDisease)
                informationAutoCompleteDisease.onItemClickListener =
                    autoComplete(
                        informationAutoCompleteDisease,
                        informationAutoCompleteTypeDiseaseInput,
                        informationAutoCompleteTypeDisease,
                        informationBtnGoImc
                    )

                informationBtnAction.setOnClickListener {
                    fragmentAttach?.hideKeyBoard()

                    if (!isConfirm()) {
                        toastGeneric(requireContext(), R.string.fields_messages)
                        return@setOnClickListener
                    }

                    val condition: Condition<Int?, Int?> = if (informationBtnGoImc.visibility != View.VISIBLE) {
                        var first = 0
                        var second = 0
                        itemsDisease.forEachIndexed { index, text ->
                            if (text == informationAutoCompleteDisease.text.toString()) {
                                first = index
                            }
                        }
                        itemsTypeDisease.forEachIndexed { index, text ->
                            if (text == informationAutoCompleteTypeDisease.text.toString()) {
                                second = index
                            }
                        }

                        Condition(first, second)
                    } else {
                        var first = 0
                        itemsDisease.forEachIndexed { index, text ->
                            if (text == informationAutoCompleteDisease.text.toString()) {
                                first = index
                            }
                        }
                         val second = informationBtnGoImc.text.toString().toInt()

                        Condition(first, second)
                    }

                    if (isRegister) {

                        if (email != null && password != null) {
                            presenter.create(
                                email = email!!,
                                password = password!!,
                                name = informationEditName.text.toString(),
                                age = informationEditAge.text.toString().toInt(),
                                sex = masOrFem(informationRadioMasculine.isChecked),
                                condition = listOf(condition)
                            )
                        }

                    } else {
                        presenter.updateProfile(
                            name = informationEditName.text.toString(),
                            age = informationEditAge.text.toString().toInt(),
                            sex = masOrFem(informationRadioMasculine.isChecked),
                            condition = listOf(condition)
                        )
                    }
                }

//                a.setOnClickListener {
//                    val autoComplete = AutoCompleteTextView(informationAutoCompleteTypeDisease.context, null, 0, R.style.Theme_SaudeDigital_AutoComplete)
//                    val textInputLayout = TextInputLayout(informationAutoCompleteTypeDiseaseInput.context, null, informationAutoCompleteDiseaseInput.explicitStyle)
//                    textInputLayout.addView(autoComplete)
//
//                    informationContainerForm.addView(textInputLayout, (informationContainerForm.childCount - 2))
//                }

                registerBtnLogin.setOnClickListener {
                    fragmentAttachRegister?.goToLoginScreen()
                }

                informationBtnGoImc.setOnClickListener {
                    imcActivityResult.launch(Intent(requireContext(), ImcActivity::class.java))
                }
            }
        }
    }

    override fun showProgress(enabled: Boolean) {
        binding?.informationProgress?.visibility = if (enabled) View.VISIBLE else View.GONE
    }

    override fun displaySuccessFetch(data: User) {
        binding?.informationEditName?.setText(data.name?.toCharArray(), 0, data.name!!.length)

        binding?.informationEditAge?.setText(
            data.age?.toString()?.toCharArray(),
            0,
            data.age!!.toString().length
        )
    }

    override fun displayFailureFetch(message: String) {
        toastGeneric(requireContext(), message)
    }

    override fun displaySuccessCreate() {
        toastGeneric(requireContext(), R.string.create_success)

        val fragment = RegisterPhotoFragment().apply {
            arguments = Bundle().apply {
                putString(
                    RegisterPhotoFragment.PHOTO,
                    binding?.informationEditName?.text.toString()
                )
            }
        }
        fragmentAttachRegister?.replaceFragment(fragment)
    }

    override fun displayFailureCreate(message: String) {
        toastGeneric(requireContext(), message)
    }

    override fun displaySuccessUpdate() {
        toastGeneric(requireContext(), R.string.update_success)
        findNavController().navigateUp()
    }

    override fun displayFailureUpdate(message: String) {
        toastGeneric(requireContext(), message)
    }

    private fun autoComplete(
        autoDisease: AutoCompleteTextView,
        typeInput: TextInputLayout,
        autoType: AutoCompleteTextView,
        btnImc: Button
    ): AdapterView.OnItemClickListener {
        return AdapterView.OnItemClickListener { parent, view, position, id ->
            when (autoDisease.text.toString()) {
                itemsDisease[0] -> {
                    fragmentAttach?.hideKeyBoard()
                    typeInput.visibility = View.GONE
                    btnImc.visibility = View.GONE
                }

                itemsDisease[1] -> {
                    fragmentAttach?.hideKeyBoard()
                    typeInput.visibility = View.GONE
                    btnImc.visibility = View.VISIBLE
                    imc?.let { btnImc.text = it }
                }

                else -> {
                    fragmentAttach?.hideKeyBoard()
                    typeInput.visibility = View.VISIBLE
                    btnImc.visibility = View.GONE
                    setAdapterAutoComplete(autoType, itemsTypeDisease)
                }
            }

        }
    }

    private fun setAdapterAutoComplete(
        autoComplete: AutoCompleteTextView,
        items: Array<String>
    ) {
        if (autoComplete.id == binding?.informationAutoCompleteTypeDisease?.id) {
            binding?.informationAutoCompleteTypeDiseaseInput?.visibility = View.VISIBLE
            binding?.informationBtnGoImc?.visibility = View.GONE
        }

        autoComplete.setText(items.first())
        autoComplete.setAdapter(
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, items)
        )
    }

    private fun masOrFem(isMasculine: Boolean): Int {
        return if (isMasculine) R.string.masculine else R.string.female
    }

    private fun isConfirm(): Boolean {
        if (binding?.informationEditName?.text?.toString()?.length!! >= 3 &&
            binding?.informationEditAge?.text?.toString()?.isNotEmpty()!! &&
            !binding?.informationEditAge?.text?.toString()?.startsWith("0")!! &&
            binding?.informationAutoCompleteDisease?.text?.toString() != itemsDisease[0]
        ) {

            if (binding?.informationBtnGoImc?.visibility!! == View.VISIBLE && imc == null) return false

            if (binding?.informationAutoCompleteTypeDisease?.visibility == View.VISIBLE &&
                binding?.informationAutoCompleteTypeDisease?.text?.toString()!! == itemsTypeDisease[0]
            ) return false

            if (!binding?.informationRadioMasculine?.isChecked!! &&
                !binding?.informationRadioFemale?.isChecked!!
            ) return false

            return true
        }

        return false

    }

    private val imcActivityResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activity ->
            if (activity.resultCode == Activity.RESULT_OK) {
                imc?.let {
                    binding?.informationBtnGoImc?.text = it
                }
            }
        }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is AttachListener)
            fragmentAttach = context

    }

    override fun onDestroy() {
        fragmentAttach = null
        binding = null
        imc = null
        super.onDestroy()
    }
}