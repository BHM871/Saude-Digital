package com.blackholecode.saudedigital.common.base

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import com.blackholecode.saudedigital.main.view.MainActivity

abstract class BaseFragment<B, P : BasePresenter>(@LayoutRes layoutId: Int, open val bind: (View) -> B) : Fragment(layoutId) {

    protected var binding: B? = null
    abstract var presenter: P

    protected var scroll: MainActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getMenu()?.let { setHasOptionsMenu(true) }

        setupPresenter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = bind(view)
        scroll?.setScrollToolbarEnabled()

        setupView()
    }

    abstract fun setupPresenter()

    abstract fun setupView()

    @MenuRes
    open fun getMenu() : Int? {
        return null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        getMenu()?.let {
            menu.clear()
            inflater.inflate(it, menu)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is MainActivity)
            scroll = context
    }

    override fun onDestroy() {
        binding = null
        scroll = null
        presenter.onDestroy()
        super.onDestroy()
    }


}