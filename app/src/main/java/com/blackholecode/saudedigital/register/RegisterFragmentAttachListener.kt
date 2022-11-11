package com.blackholecode.saudedigital.register

import androidx.fragment.app.Fragment
import com.blackholecode.saudedigital.common.base.AttachListener

interface RegisterFragmentAttachListener : AttachListener {
    fun goToLoginScreen() {throw UnsupportedOperationException()}
    fun goToMainScreen() {throw UnsupportedOperationException()}

    fun replaceFragment(fragment: Fragment) {throw UnsupportedOperationException()}
}