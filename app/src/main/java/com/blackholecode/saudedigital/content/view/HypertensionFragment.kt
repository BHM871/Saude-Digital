package com.blackholecode.saudedigital.content.view

import android.annotation.SuppressLint
import androidx.recyclerview.widget.LinearLayoutManager
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.common.model.Video
import com.blackholecode.saudedigital.content.base.ContentBaseFragment
import com.blackholecode.saudedigital.databinding.FragmentHypertensionBinding
import java.util.*

class HypertensionFragment : ContentBaseFragment<FragmentHypertensionBinding>(
    R.layout.fragment_hypertension,
    FragmentHypertensionBinding::bind
) {

    @SuppressLint("NotifyDataSetChanged")
    override fun setupView() {
        binding?.let { binding ->
            with(binding) {
                val list = mutableListOf<Video>()
                for (i in 0 until 10) {
                    list.add(
                        Video(
                            id = UUID.randomUUID().toString(),
                            thumbnail = R.drawable.ic_heart,
                            title = "Hipertensão$i",
                            description = getString(R.string.lorem),
                            type = "Hypertension"
                        )
                    )
                }

                adapterRv.items = list.toMutableList()
                adapterRv.notifyDataSetChanged()

                hypertensionRecyclerVideos.layoutManager = LinearLayoutManager(requireContext())
                hypertensionRecyclerVideos.adapter = adapterRv
            }
        }
    }

}