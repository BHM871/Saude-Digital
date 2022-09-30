package com.blackholecode.saudedigital.profile.view

import androidx.navigation.fragment.findNavController
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.common.base.BaseFragment
import com.blackholecode.saudedigital.databinding.FragmentProfileBinding

class ProfileFragment : BaseFragment<FragmentProfileBinding>(
    R.layout.fragment_profile,
    FragmentProfileBinding::bind
) {

    companion object {
        const val NAME = "name"
        const val AGE = "age"
        const val SEX = "sex"
        const val CONDITION = "condition"

        var name: String? = null
        var age: Int? = null
        var sex: Boolean? = null
        var condition: String? = null
    }

    override fun setupView() {

        name = arguments?.getString(NAME)
        age = arguments?.getInt(AGE)
        sex = arguments?.getBoolean(SEX)
        condition = arguments?.getString(CONDITION)

        binding?.let { binding ->
            with(binding) {
                name?.let { profileTxtName.text = getString(R.string.profile_name, name) }
                age?.let { profileTxtAge.text = getString(R.string.profile_age, age) }
                sex?.let { profileTxtSex.text = getString(R.string.profile_sex, masOrFem()) }
                condition?.let { profileTxtCondition.text = getString(R.string.profile_condition, condition) }

                profileFloatingEdit.setOnClickListener {
                    findNavController().navigate(R.id.action_nav_profile_to_nav_edit_information)
                }
            }
        }
    }

    private fun masOrFem() : String? {
        sex?.let {
            return if (!it) getString(R.string.masculine) else getString(R.string.female)
        }

        return null
    }

}