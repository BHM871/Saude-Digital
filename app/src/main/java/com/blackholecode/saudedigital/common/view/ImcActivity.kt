package com.blackholecode.saudedigital.common.view

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.common.extension.closeKeyboard
import com.blackholecode.saudedigital.common.extension.toastGeneric
import com.blackholecode.saudedigital.databinding.ActivityImcBinding
import com.blackholecode.saudedigital.common.util.information.view.InformationFragment

class ImcActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImcBinding

    @RequiresApi(Build.VERSION_CODES.P)
    @SuppressLint("ServiceCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityImcBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.imcBtnCalcu.setOnClickListener {
            if (!validate()) {
                toastGeneric(this, R.string.fields_messages)
                return@setOnClickListener
            }

            val imc = calcuImc()

            closeKeyboard()

            InformationFragment.imc = response(imc)
            setResult(RESULT_OK)
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun response(imc: Double): Int {
        return when {
            imc < 15 -> R.string.imc_severely_low_weight
            imc < 16 -> R.string.imc_very_low_weight
            imc < 18.5 -> R.string.imc_low_weight
            imc < 25 -> R.string.normal
            imc < 30 -> R.string.imc_high_weight
            imc < 35 -> R.string.imc_so_high_weight
            imc < 40 -> R.string.imc_severely_high_weight
            else -> R.string.imc_extreme_weight
        }

    }

    private fun calcuImc(): Double {
        val weight: Double = binding.imcEditWeight.text.toString().toDouble()
        val height: Double = binding.imcEditHeight.text.toString().toDouble()
        return weight / (height * height)
    }

    private fun validate(): Boolean {
        return (binding.imcEditWeight.text.toString().isNotEmpty()
                && binding.imcEditHeight.text.toString().isNotEmpty()
                && !binding.imcEditWeight.text.toString().startsWith("0")
                && !binding.imcEditHeight.text.toString().startsWith("0"))
    }

}