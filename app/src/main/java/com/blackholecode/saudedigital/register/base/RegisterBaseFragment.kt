package com.blackholecode.saudedigital.register.base

import android.content.Context
import android.view.View
import androidx.annotation.LayoutRes
import com.blackholecode.saudedigital.common.base.BaseFragment
import com.blackholecode.saudedigital.register.FragmentAttachListener

abstract class RegisterBaseFragment<B>(@LayoutRes layout: Int, bind: (View) -> B) : BaseFragment<B>(layout, bind) {

    protected var fragmentAttach: FragmentAttachListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is FragmentAttachListener)
            fragmentAttach = context
    }

    override fun onDestroy() {
        fragmentAttach = null
        super.onDestroy()
    }

}