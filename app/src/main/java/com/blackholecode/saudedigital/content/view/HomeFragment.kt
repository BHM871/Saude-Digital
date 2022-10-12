package com.blackholecode.saudedigital.content.view

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.common.base.DependencyInjector
import com.blackholecode.saudedigital.common.extension.toastGeneric
import com.blackholecode.saudedigital.common.model.MContent
import com.blackholecode.saudedigital.content.Content
import com.blackholecode.saudedigital.content.base.ContentBaseFragment
import com.blackholecode.saudedigital.databinding.FragmentContenContentBinding
import java.util.*

class HomeFragment : ContentBaseFragment<FragmentContenContentBinding, Content.Presenter>(
    R.layout.fragment_conten_content,
    FragmentContenContentBinding::bind
), Content.View {

    override lateinit var presenter: Content.Presenter

    override fun setupPresenter() {
        presenter = DependencyInjector.contentPresenter(this)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setupView() {
        presenter.fetchContent("home")

        binding?.let { binding ->
            with(binding) {
                val list = mutableListOf<MContent>()
                for (i in 0 until 3) {
                    list.add(
                        MContent(
                            id = UUID.randomUUID().toString(),
                            thumbnail = R.drawable.ic_insulin,
                            title = "Diabetes$i",
                            description = getString(R.string.lorem),
                            videoUrl = "",
                            type = "diabetes"
                        )
                    )
                    list.add(
                        MContent(
                            id = UUID.randomUUID().toString(),
                            thumbnail = R.drawable.ic_heart,
                            title = "Hipertensão$i",
                            description = getString(R.string.lorem),
                            videoUrl = "",
                            type = "hypertension"
                        )
                    )
                    list.add(
                        MContent(
                            id = UUID.randomUUID().toString(),
                            thumbnail = R.drawable.ic_fat,
                            title = "Obesidade$i",
                            description = getString(R.string.lorem),
                            videoUrl = "",
                            type = "obesity"
                        )
                    )
                }

                adapterRv.items = list.toMutableList()
                adapterRv.notifyDataSetChanged()

                contentRecyclerVideos.layoutManager = LinearLayoutManager(requireContext())
                contentRecyclerVideos.adapter = adapterRv
            }
        }
    }

    override fun showProgress(enabled: Boolean) {
        binding?.contentProgress?.visibility = if (enabled) View.VISIBLE else View.GONE
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun displayRequestSuccessful(data: List<MContent>) {
        binding?.contentListEmpty?.visibility = View.GONE
        binding?.contentRecyclerVideos?.visibility = View.VISIBLE
        adapterRv.items = data.toMutableList()
        adapterRv.notifyDataSetChanged()
    }

    override fun displayRequestEmptyList() {
        binding?.contentRecyclerVideos?.visibility = View.GONE
        binding?.contentListEmpty?.visibility = View.VISIBLE
    }

    override fun displayRequestFailure(message: String) {
        toastGeneric(requireContext(), message)
    }

}