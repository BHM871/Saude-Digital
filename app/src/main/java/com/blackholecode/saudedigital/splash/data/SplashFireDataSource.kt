package com.blackholecode.saudedigital.splash.data

import android.os.Handler
import android.os.Looper
import com.blackholecode.saudedigital.common.base.RequestCallback
import com.blackholecode.saudedigital.common.model.User
import java.util.*

class SplashFireDataSource : SplashDataSource {

    override fun log(callback: RequestCallback<User>) {
        Handler(Looper.getMainLooper()).postDelayed({
            callback.onSuccess(
                User(
                    UUID.randomUUID().toString(),
                    "adrianalmeida417@gmail.com",
                    "123123123",
                    "Adrian",
                    17,
                    "Masculino",
                    listOf(
                        Pair("Diabetes", "Tipo 1")
                    )
                )
            )
            callback.onComplete()
        }, 1000)
    }

}