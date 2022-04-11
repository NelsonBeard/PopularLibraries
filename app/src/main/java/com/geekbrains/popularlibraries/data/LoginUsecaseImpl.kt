package com.geekbrains.popularlibraries.data

import android.os.Handler
import com.geekbrains.popularlibraries.domain.LoginApi
import com.geekbrains.popularlibraries.domain.LoginUsecase

class LoginUsecaseImpl(
    private val api: LoginApi,
    private val uiHandler: Handler
) : LoginUsecase {
    override fun login(
        login: String,
        password: String,
        callback: (Boolean) -> Unit
    ) {
        Thread {
            val result = api.login(login, password)
            uiHandler.post {
                callback(result)
            }
        }.start()
    }
}