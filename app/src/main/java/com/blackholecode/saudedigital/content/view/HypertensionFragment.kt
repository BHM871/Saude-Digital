package com.blackholecode.saudedigital.content.view

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.common.base.DependencyInjector
import com.blackholecode.saudedigital.common.extension.toastGeneric
import com.blackholecode.saudedigital.common.model.ModelContent
import com.blackholecode.saudedigital.content.Content
import com.blackholecode.saudedigital.content.base.ContentBaseFragment
import com.blackholecode.saudedigital.databinding.FragmentContentContentBinding

class HypertensionFragment : ContentBaseFragment<FragmentContentContentBinding, Content.Presenter>(
    R.layout.fragment_content_content,
    FragmentContentContentBinding::bind
), Content.View {

    override lateinit var presenter: Content.Presenter

    override fun setupPresenter() {
        presenter = DependencyInjector.contentPresenter(requireActivity(), this)
    }

    override fun onResume() {
        super.onResume()
        presenter.fetchContent("hypertension")
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setupView() {
        binding?.contentRecycler?.layoutManager = LinearLayoutManager(requireContext())
        binding?.contentRecycler?.adapter = adapterRv
    }

    override fun showProgress(enabled: Boolean) {
        binding?.contentProgress?.visibility = if (enabled) View.VISIBLE else View.GONE
    }

    override fun displayRequestSuccessful(data: List<ModelContent>) {
        setupRecycler(data)
        binding?.contentRecycler?.visibility = View.VISIBLE
        binding?.contentTxtEmpty?.visibility = View.GONE
    }

    override fun displayRequestEmptyList() {
        binding?.contentRecycler?.visibility = View.GONE
        binding?.contentTxtEmpty?.visibility = View.VISIBLE
    }

    override fun displayRequestFailure(message: String) {
        toastGeneric(requireContext(), message)
        binding?.contentRecycler?.visibility = View.GONE
        binding?.contentTxtEmpty?.visibility = View.VISIBLE
    }

}