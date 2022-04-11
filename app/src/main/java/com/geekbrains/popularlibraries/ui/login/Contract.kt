package com.geekbrains.popularlibraries.ui.login

import androidx.annotation.MainThread

class Contract {

    interface View {
        @MainThread
        fun setSuccess()

        @MainThread
        fun setError(error: String)

        @MainThread
        fun showProgress()

        @MainThread
        fun hideProgress()

        @MainThread
        fun setRegistration()

        @MainThread
        fun setPasswordHelper(PASSWORD: String)
    }

    interface Presenter {
        fun onAttach(view: View)
        fun onLogin(login: String, password: String)
        fun onRegistration()
        fun onShowPassword()
    }
}