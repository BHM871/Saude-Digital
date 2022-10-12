package com.blackholecode.saudedigital.common.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.blackholecode.saudedigital.main.view.MainActivity

abstract class BaseFragment<B, P : BasePresenter>(@LayoutRes layoutId: Int, open val bind: (View) -> B) : Fragment(layoutId) {

    protected var binding: B? = null
    abstract var presenter: P

    protected var scroll: MainActivity? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = bind(view)
        scroll?.setScrollToolbarEnabled()

        setupPresenter()
        setupView()
    }

    abstract fun setupPresenter()

    abstract fun setupView()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is MainActivity)
            scroll = context
    }

    override fun onDestroy() {
        binding = null
        scroll = null
        super.onDestroy()
    }


}