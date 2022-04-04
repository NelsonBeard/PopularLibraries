package com.geekbrains.popularlibraries.presenter

import android.os.Handler
import android.os.Looper
import com.geekbrains.popularlibraries.Contract
import com.geekbrains.popularlibraries.model.PASSWORD


class Presenter : Contract.Presenter {
    private var view: Contract.View? = null
    private val uiHandler = Handler(Looper.getMainLooper())
    private var isSuccess: Boolean = false
    private var errorText: String = ""

    override fun onAttach(view: Contract.View) {
        this.view = view
        if (isSuccess) {
            view.setSuccess()
        } else {
            view.setError(errorText)
        }
    }

    override fun onLogin(login: String, password: String) {
        view?.showProgress()
        Thread {
            Thread.sleep(1_000)
            uiHandler.post {
                view?.hideProgress()
                if (checkCredentials(login, password)) {
                    view?.setSuccess()
                    isSuccess = true
                    errorText = ""
                } else {
                    view?.setError(errorText)
                    isSuccess = false
                }
            }
        }.start()
    }

    private fun checkCredentials(login: String, password: String): Boolean {
        if (login == "") {
            errorText = "Введите логин"
            return false
        } else if (password == PASSWORD) {
            return true
        }
        errorText = "Неверный пароль"
        return false
    }

    override fun onRegistration() {
        view?.setRegistration()
    }

    override fun onShowPassword() {
        view?.setPasswordHelper(PASSWORD)
    }

}