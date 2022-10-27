package com.blackholecode.saudedigital.common.view

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.databinding.DialogCustomBinding

@SuppressLint("SupportAnnotationUsage")
class CustomDialog(context: Context) : Dialog(context) {

    private lateinit var binding: DialogCustomBinding

    private var windowBackground: Int? = null

    private lateinit var txtOptions: Array<TextView>

    private var titleId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DialogCustomBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }

    @DrawableRes
    fun setWindowBackground(windowBackground: Int) {
        this.windowBackground = windowBackground
    }

    override fun setTitle(titleId: Int) {
        this.titleId = titleId
    }

    fun addOption(vararg options: Int, listener: View.OnClickListener) {
        txtOptions = Array(options.size){
            TextView(context)
        }
        options.forEachIndexed { index, text ->
            txtOptions[index].apply {
                id = text
                setText(text)
                setOnClickListener{
                    listener.onClick(it)
                    dismiss()
                }
            }
        }
    }

    override fun show() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.show()

        titleId?.let {
            binding.dialogTxtTitle.setText(titleId!!)
        }

        for (textView in txtOptions) {
            val layoutParamsText = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            layoutParamsText.setMargins(30,50,30,50)

            binding.container.addView(textView, layoutParamsText)

            val layoutParamsView = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 2)
            val view = View(context)
            view.setBackgroundResource(R.color.border_gray)

            binding.container.addView(view, layoutParamsView)

        }
    }

}