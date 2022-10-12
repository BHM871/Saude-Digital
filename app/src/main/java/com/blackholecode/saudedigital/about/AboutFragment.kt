package com.blackholecode.saudedigital.about

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.BuildConfig
import com.blackholecode.saudedigital.databinding.FragmentAboutBinding

class AboutFragment : Fragment(R.layout.fragment_about) {

    private var binding: FragmentAboutBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAboutBinding.bind(view)

        binding?.aboutImgLogo?.setImageResource(R.mipmap.ic_launcher_round)
        binding?.aboutTxtVersion?.text = getString(R.string.about_version, BuildConfig.VERSION_NAME)
        binding?.aboutTxtObjective?.text = getString(R.string.about_objective, getString(R.string.lorem))
        binding?.aboutTxtDeveloper?.text = getString(R.string.about_developer, "Adrian Almeida - BHM")
        binding?.aboutTxtFisio?.text = getString(R.string.about_fisio, "Aquele moço")
        binding?.aboutTxtNutri?.text = getString(R.string.about_nutri, "Aquela moça")
        binding?.aboutTxtGit?.text = getString(R.string.github)
        binding?.aboutTxtLinkedin?.text = getString(R.string.linkedin)
    }

}