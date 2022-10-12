package com.blackholecode.saudedigital.common.util.information.view

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
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.common.base.DependencyInjector
import com.blackholecode.saudedigital.common.extension.toastGeneric
import com.blackholecode.saudedigital.common.util.information.Information
import com.blackholecode.saudedigital.databinding.FragmentInformationBinding
import com.blackholecode.saudedigital.imc.view.ImcActivity
import com.blackholecode.saudedigital.profile.view.ProfileFragment
import com.blackholecode.saudedigital.register.FragmentAttachListener
import com.blackholecode.saudedigital.register.view.RegisterActivity
import com.google.android.material.textfield.TextInputLayout

class InformationFragment : Fragment(R.layout.fragment_information), Information.View {

    companion object {
        var imc: String? = null
    }

    override lateinit var presenter: Information.Presenter
    private var isRegister: Boolean = false

    private var binding: FragmentInformationBinding? = null
    private var fragmentAttach: FragmentAttachListener? = null

    private lateinit var itemsDisease: Array<String>
    private lateinit var itemsTypeDisease: Array<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInformationBinding.bind(view)
        presenter = DependencyInjector.informationPresenter(this)
        isRegister = (activity?.javaClass?.simpleName == RegisterActivity().javaClass.simpleName)
        setupView()
    }

    @SuppressLint("ResourceType", "NewApi")
    fun setupView() {
        itemsDisease = resources.getStringArray(R.array.disease)
        itemsTypeDisease = resources.getStringArray(R.array.type_disease)

        if (!isRegister) {
            binding?.informationContainerLogin?.visibility = View.GONE
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

                informationBtnFinish.setOnClickListener {
                    fragmentAttach?.hideKeyBoard()

                    if (!isConfirm()) {
                        toastGeneric(requireContext(), R.string.fields_messages)
                        return@setOnClickListener
                    }

                    if (isRegister) {
                        fragmentAttach?.goToMainScreen()
                    } else {
                        val bundle = Bundle()
                        bundle.putString(ProfileFragment.NAME, informationEditName.text.toString())
                        bundle.putInt(
                            ProfileFragment.AGE,
                            informationEditAge.text.toString().toInt()
                        )
                        bundle.putBoolean(ProfileFragment.SEX, informationRadioMasculine.isSelected)

                        if (informationBtnGoImc.visibility != View.VISIBLE) {
                            bundle.putString(
                                ProfileFragment.CONDITION,
                                "${informationAutoCompleteDisease.text} - ${informationAutoCompleteTypeDisease.text}"
                            )
                        } else {
                            bundle.putString(
                                ProfileFragment.CONDITION,
                                "${informationAutoCompleteDisease.text} - ${informationBtnGoImc.text}"
                            )
                        }

                        imc = null

                        findNavController().navigate(
                            R.id.action_nav_edit_information_to_nav_profile,
                            bundle,
                            null,
                            null
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
                    fragmentAttach?.goToLoginScreen()
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

    override fun displaySuccessCreate() {
        if (isRegister) {
            toastGeneric(requireContext(), R.string.create_success)
        } else {
            toastGeneric(requireContext(), R.string.update_success)
        }
    }

    override fun displayFailureCreate(message: String) {
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

        if (context is FragmentAttachListener)
            fragmentAttach = context
    }

    override fun onDestroy() {
        fragmentAttach = null
        binding = null
        imc = null
        super.onDestroy()
    }
}