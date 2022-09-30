package com.blackholecode.saudedigital.register

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment

interface FragmentAttachListener {

    fun goToLoginScreen()

    fun goToMainScreen()

    fun replaceFragment(fragment: Fragment)

    fun hideKeyBoard ()

}