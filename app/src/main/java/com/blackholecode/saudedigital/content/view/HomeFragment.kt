package com.blackholecode.saudedigital.content.view

import android.annotation.SuppressLint
import androidx.recyclerview.widget.LinearLayoutManager
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.common.model.Video
import com.blackholecode.saudedigital.content.base.ContentBaseFragment
import com.blackholecode.saudedigital.databinding.FragmentHomeBinding
import java.util.*

class HomeFragment : ContentBaseFragment<FragmentHomeBinding>(
    R.layout.fragment_home,
    FragmentHomeBinding::bind
) {

    @SuppressLint("NotifyDataSetChanged")
    override fun setupView() {

        binding?.let { binding ->
            with(binding) {
                val list = mutableListOf<Video>()
                for (i in 0 until 3) {
                    list.add(
                        Video(
                            id = UUID.randomUUID().toString(),
                            thumbnail = R.drawable.ic_insulin,
                            title = "Diabetes$i",
                            description = getString(R.string.lorem),
                            type = "Diabetes"
                        )
                    )
                    list.add(
                        Video(
                            id = UUID.randomUUID().toString(),
                            thumbnail = R.drawable.ic_heart,
                            title = "Hipertensão$i",
                            description = getString(R.string.lorem),
                            type = "Hypertensio"
                        )
                    )
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

                homeRecyclerVideos.layoutManager = LinearLayoutManager(requireContext())
                homeRecyclerVideos.adapter = adapterRv
            }
        }
    }

}