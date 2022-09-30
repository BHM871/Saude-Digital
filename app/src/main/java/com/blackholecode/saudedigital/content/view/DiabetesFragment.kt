package com.blackholecode.saudedigital.content.view

import android.annotation.SuppressLint
import androidx.recyclerview.widget.LinearLayoutManager
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.common.model.Video
import com.blackholecode.saudedigital.content.base.ContentBaseFragment
import com.blackholecode.saudedigital.databinding.FragmentDiabetesBinding
import java.util.*

class DiabetesFragment : ContentBaseFragment<FragmentDiabetesBinding>(
    R.layout.fragment_diabetes,
    FragmentDiabetesBinding::bind
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
                            thumbnail = R.drawable.ic_insulin,
                            title = "Diabetes$i",
                            description = getString(R.string.lorem),
                            type = "Diabetes"
                        )
                    )
                }

                adapterRv.items = list.toMutableList()
                adapterRv.notifyDataSetChanged()

                diabetesRecyclerVideos.layoutManager = LinearLayoutManager(requireContext())
                diabetesRecyclerVideos.adapter = adapterRv
            }
        }
    }
}