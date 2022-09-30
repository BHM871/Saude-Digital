package com.blackholecode.saudedigital.common.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.common.extension.toastGeneric
import com.blackholecode.saudedigital.databinding.FragmentInformationBinding
import com.blackholecode.saudedigital.imc.view.ImcActivity
import com.blackholecode.saudedigital.main.view.MainActivity
import com.blackholecode.saudedigital.profile.view.ProfileFragment
import com.blackholecode.saudedigital.register.base.RegisterBaseFragment
import com.blackholecode.saudedigital.register.view.RegisterActivity

class FragmentInformation : RegisterBaseFragment<FragmentInformationBinding>(
    R.layout.fragment_information,
    FragmentInformationBinding::bind
) {

    companion object {
        var imc: Double? = null
    }

    private lateinit var itemsDisease: Array<String>
    private lateinit var itemsTypeDisease: Array<String>

    @SuppressLint("ResourceType")
    override fun setupView() {
        itemsDisease = resources.getStringArray(R.array.disease)
        itemsTypeDisease = resources.getStringArray(R.array.type_disease)

        if (activity?.javaClass?.simpleName == MainActivity().javaClass.simpleName) {
            binding?.informationContainerLogin?.visibility = View.GONE
        }

        binding?.let { binding ->
            with(binding) {
                setAdapterAutoComplete(informationAutoCompleteDisease, itemsDisease)
                informationAutoCompleteDisease.onItemClickListener = autoComplete

                informationBtnFinish.setOnClickListener {
                    fragmentAttach?.hideKeyBoard()

                    if (!isConfirm()) {
                        toastGeneric(requireContext(), R.string.fields_messages)
                        return@setOnClickListener
                    }

                    if (activity?.javaClass?.simpleName == RegisterActivity().javaClass.simpleName) {
                        fragmentAttach?.goToMainScreen()
                    } else {
                        val bundle = Bundle()
                        bundle.putString(ProfileFragment.NAME, informationEditName.text.toString())
                        bundle.putInt(
                            ProfileFragment.AGE,
                            informationEditAge.text.toString().toInt()
                        )
                        bundle.putBoolean(ProfileFragment.SEX, informationRadioMasculine.isSelected)

                        if (imc == null) {
                            bundle.putString(
                                ProfileFragment.CONDITION,
                                "${informationAutoCompleteDisease.text} - ${informationAutoCompleteTypeDisease.text}"
                            )
                        } else {
                            bundle.putString(
                                ProfileFragment.CONDITION,
                                "${informationAutoCompleteDisease.text} - ${informationBtnFinish.text}"
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

                registerBtnLogin.setOnClickListener {
                    fragmentAttach?.goToLoginScreen()
                }

                informationBtnGoImc.setOnClickListener {
                    imcActivityResult.launch(Intent(requireContext(), ImcActivity::class.java))
                }
            }
        }
    }

    private val autoComplete = AdapterView.OnItemClickListener { parent, view, position, id ->
        when (binding?.informationAutoCompleteDisease?.text?.toString()) {
            itemsDisease[1] -> {
                binding?.informationAutoCompleteTypeDiseaseInput?.visibility = View.GONE
                binding?.informationBtnGoImc?.visibility = View.VISIBLE
                imc?.let { binding?.informationBtnGoImc?.text = it.toString() }
            }

            itemsDisease[2] -> {
                setAdapterAutoComplete(
                    binding?.informationAutoCompleteTypeDisease!!,
                    itemsTypeDisease
                )
                binding?.informationAutoCompleteTypeDiseaseInput?.visibility = View.VISIBLE
                binding?.informationBtnGoImc?.visibility = View.GONE
            }

            itemsDisease[3] -> {
                setAdapterAutoComplete(
                    binding?.informationAutoCompleteTypeDisease!!,
                    itemsTypeDisease
                )
                binding?.informationAutoCompleteTypeDiseaseInput?.visibility = View.VISIBLE
                binding?.informationBtnGoImc?.visibility = View.GONE
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
                    binding?.informationBtnGoImc?.text = getString(R.string.format_number, it)
                }
            }
        }

    override fun onDestroy() {
        binding = null
        imc = null
        super.onDestroy()
    }
}