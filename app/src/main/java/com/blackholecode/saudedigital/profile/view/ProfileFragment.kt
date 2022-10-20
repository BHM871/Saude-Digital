package com.blackholecode.saudedigital.profile.view

import android.content.Context
import android.view.MenuItem
import android.view.View
import androidx.navigation.fragment.findNavController
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.common.base.BaseFragment
import com.blackholecode.saudedigital.common.base.DependencyInjector
import com.blackholecode.saudedigital.common.extension.toastGeneric
import com.blackholecode.saudedigital.common.model.User
import com.blackholecode.saudedigital.databinding.FragmentProfileBinding
import com.blackholecode.saudedigital.main.MainFragmentAttachListener
import com.blackholecode.saudedigital.profile.Profile

class ProfileFragment : BaseFragment<FragmentProfileBinding, Profile.Presenter>(
    R.layout.fragment_profile,
    FragmentProfileBinding::bind
), Profile.View {

    private var mainAttach: MainFragmentAttachListener? = null

    override lateinit var presenter: Profile.Presenter

    override fun setupPresenter() {
        presenter = DependencyInjector.profilePresenter(this)
    }

    override fun setupView() {
        presenter.fetchProfile()

        binding?.let { binding ->
            with(binding) {
                profileFloatingEdit.setOnClickListener {
                    findNavController().navigate(R.id.action_nav_profile_to_nav_edit_information)
                }
            }
        }
    }

    override fun getMenu(): Int {
        return R.menu.menu_profile
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId) {
            R.id.menu_logout -> {
                mainAttach?.logout()
                true
            }
            else -> false
        }
    }

    override fun showProgress(enabled: Boolean) {
        binding?.profileProgress?.visibility = if (enabled) View.VISIBLE else View.GONE
    }

    override fun displayFetchSuccess(data: User) {
        binding?.profileTxtName?.text = getString(R.string.profile_name, data.name)
        binding?.profileTxtAge?.text = getString(R.string.profile_age, data.age)
        binding?.profileTxtSex?.text = getString(R.string.profile_sex, data.sex)

        var listCondition = ""
        for (item in data.condition!!){
            listCondition = "${ item.first } - ${ item.second }\n"
        }

        binding?.profileTxtCondition?.text = getString(R.string.profile_condition, listCondition)
    }

    override fun displayFetchFailure(message: String) {
        toastGeneric(requireContext(), message)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is MainFragmentAttachListener)
            mainAttach = context
    }

    override fun onDestroy() {
        mainAttach = null
        super.onDestroy()
    }

}