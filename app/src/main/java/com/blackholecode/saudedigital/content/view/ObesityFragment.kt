package com.blackholecode.saudedigital.content.view

import android.annotation.SuppressLint
import androidx.recyclerview.widget.LinearLayoutManager
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.common.model.Video
import com.blackholecode.saudedigital.content.base.ContentBaseFragment
import com.blackholecode.saudedigital.databinding.FragmentObesityBinding
import java.util.*

class ObesityFragment : ContentBaseFragment<FragmentObesityBinding>(
    R.layout.fragment_obesity,
    FragmentObesityBinding::bind
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
                            thumbnail = R.drawable.ic_fat,
                            title = "Obesidade$i",
                            description = getString(R.string.lorem),
                            type = "Obesidade"
                        )
                    )
                }

                adapterRv.items = list.toMutableList()
                adapterRv.notifyDataSetChanged()

                obesityRecyclerVideos.layoutManager = LinearLayoutManager(requireContext())
                obesityRecyclerVideos.adapter = adapterRv
            }
        }
    }

}