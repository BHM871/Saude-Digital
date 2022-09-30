package com.blackholecode.saudedigital.common.util

import android.text.Editable
import android.text.TextWatcher

class TxtWatch(private val onTxtWatch: (String) -> Unit) : TextWatcher {

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        onTxtWatch.invoke(s.toString())
    }

    override fun afterTextChanged(s: Editable?) {
    }
}