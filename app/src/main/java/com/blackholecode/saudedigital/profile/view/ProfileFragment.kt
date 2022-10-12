package com.blackholecode.saudedigital.profile.view

import android.view.View
import androidx.navigation.fragment.findNavController
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.common.base.BaseFragment
import com.blackholecode.saudedigital.common.base.DependencyInjector
import com.blackholecode.saudedigital.common.extension.toastGeneric
import com.blackholecode.saudedigital.common.model.User
import com.blackholecode.saudedigital.databinding.FragmentProfileBinding
import com.blackholecode.saudedigital.profile.Profile

class ProfileFragment : BaseFragment<FragmentProfileBinding, Profile.Presenter>(
    R.layout.fragment_profile,
    FragmentProfileBinding::bind
), Profile.View {

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

    override lateinit var presenter: Profile.Presenter

    override fun setupPresenter() {
        presenter = DependencyInjector.profilePresenter(this)
    }

    override fun setupView() {
        name = arguments?.getString(NAME)
        age = arguments?.getInt(AGE)
        sex = arguments?.getBoolean(SEX)
        condition = arguments?.getString(CONDITION)

        presenter.fetchProfile()

        binding?.let { binding ->
            with(binding) {
                name?.let { profileTxtName.text = getString(R.string.profile_name, name) }
                age?.let { profileTxtAge.text = getString(R.string.profile_age, age) }
                sex?.let { profileTxtSex.text = getString(R.string.profile_sex, masOrFem(it)) }
                condition?.let { profileTxtCondition.text = getString(R.string.profile_condition, condition) }

                profileFloatingEdit.setOnClickListener {
                    findNavController().navigate(R.id.action_nav_profile_to_nav_edit_information)
                }
            }
        }
    }

    override fun showProgress(enabled: Boolean) {
        binding?.profileProgress?.visibility = if (enabled) View.VISIBLE else View.GONE
    }

    override fun displayFetchSuccess(data: User) {
        binding?.profileTxtName?.text = getString(R.string.profile_name, data.name)
        binding?.profileTxtAge?.text = getString(R.string.profile_age, data.age)
        binding?.profileTxtSex?.text = getString(R.string.profile_sex, data.sex)

        val condition1 = data.condition?.forEach { element ->
            "${element.first} - ${element.second}\n"
        }

        binding?.profileTxtCondition?.text = getString(R.string.profile_condition, condition1)
    }

    override fun displayFetchFailure(message: String) {
        toastGeneric(requireContext(), message)
    }

    private fun masOrFem(isMasculine: Boolean) : String {
            return if (isMasculine) getString(R.string.masculine) else getString(R.string.female)
    }

}