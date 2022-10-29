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
import java.util.*

class HomeFragment : ContentBaseFragment<FragmentContentContentBinding, Content.Presenter>(
    R.layout.fragment_content_content,
    FragmentContentContentBinding::bind
), Content.View {

    override lateinit var presenter: Content.Presenter

    override fun setupPresenter() {
        presenter = DependencyInjector.contentPresenter(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.fetchContent()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setupView() {
        binding?.let { binding ->
            with(binding) {
                contentRecycler.layoutManager = LinearLayoutManager(requireContext())
                contentRecycler.adapter = adapterRv
            }
        }
    }

    override fun showProgress(enabled: Boolean) {
        binding?.contentProgress?.visibility = if (enabled) View.VISIBLE else View.GONE
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun displayRequestSuccessful(data: List<ModelContent>) {
        binding?.contentListEmpty?.visibility = View.GONE
        binding?.contentRecycler?.visibility = View.VISIBLE
        adapterRv.items.clear()
        adapterRv.items.addAll(data.toMutableList())
        adapterRv.notifyDataSetChanged()
    }

    override fun displayRequestEmptyList() {
        binding?.contentRecycler?.visibility = View.GONE
        binding?.contentListEmpty?.visibility = View.VISIBLE
    }

    override fun displayRequestFailure(message: String) {
        toastGeneric(requireContext(), message)
    }

}