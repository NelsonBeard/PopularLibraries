package com.geekbrains.popularlibraries.ui.login

import com.geekbrains.popularlibraries.utils.Publisher

class Contract {

    interface ViewModel {
        val shouldShowProgress: Publisher<Boolean>
        val isSuccess: Publisher<Boolean>
        val errorText: Publisher<String?>
        val registration: Publisher<Boolean>
        val helper: Publisher<Boolean>

        fun onLogin(login: String, password: String)
        fun onShowPassword()
        fun onRegistration()
    }
}