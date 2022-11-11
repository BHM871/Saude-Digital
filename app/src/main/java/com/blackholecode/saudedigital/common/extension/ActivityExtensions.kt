package com.blackholecode.saudedigital.common.extension

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.blackholecode.saudedigital.R
import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.util.Application
import com.blackholecode.saudedigital.main.view.MainActivity

fun animationEnd(callback: () -> Unit) : AnimatorListenerAdapter {
    return object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator?) {
            callback.invoke()
        }
    }
}

fun AppCompatActivity.newOrReplaceFragment(@IdRes id: Int, fragment: Fragment) {
    if (supportFragmentManager.findFragmentById(id) == null) {
        supportFragmentManager.beginTransaction().apply {
            add(id, fragment, fragment.javaClass.simpleName,)
            commit()
        }
    } else {
        supportFragmentManager.beginTransaction().apply {
            replace(id, fragment, fragment.javaClass.simpleName, )
            addToBackStack(null)
            commit()
        }
    }
}

fun Activity.closeKeyboard() {
    val service = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    service.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
}

fun toastGeneric(context: Context, resId: Int) {
    Toast.makeText(context, resId, Toast.LENGTH_LONG).show()
}
fun toastGeneric(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}