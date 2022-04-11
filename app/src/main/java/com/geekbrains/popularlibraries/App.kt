package com.geekbrains.popularlibraries

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import com.geekbrains.popularlibraries.data.LoginUsecaseImpl
import com.geekbrains.popularlibraries.data.MockLoginApiImpl
import com.geekbrains.popularlibraries.domain.LoginApi
import com.geekbrains.popularlibraries.domain.LoginUsecase

class App : Application() {
    private val loginApi: LoginApi by lazy { MockLoginApiImpl() }
    val loginUsecase: LoginUsecase by lazy {
        LoginUsecaseImpl(app.loginApi, Handler(Looper.getMainLooper()))
    }

}

val Context.app: App
    get() {
        return applicationContext as App
    }