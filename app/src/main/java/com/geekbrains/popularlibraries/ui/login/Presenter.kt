package com.geekbrains.popularlibraries.ui.login

import com.geekbrains.popularlibraries.data.PASSWORD
import com.geekbrains.popularlibraries.data.errorText
import com.geekbrains.popularlibraries.domain.LoginUsecase

class Presenter(
    private val loginUsecase: LoginUsecase
) : Contract.Presenter {
    private var view: Contract.View? = null
    private var isSuccess: Boolean = false

    override fun onAttach(view: Contract.View) {
        this.view = view
        if (isSuccess) {
            view.setSuccess()
        }
    }

    override fun onLogin(login: String, password: String) {
        view?.showProgress()

            loginUsecase.login(login, password) { result ->
                view?.hideProgress()
                if (result) {
                    view?.setSuccess()
                    isSuccess = true
                } else {
                    view?.setError(errorText)
                    isSuccess = false
                }
            }

    }

    override fun onRegistration() {
        view?.setRegistration()
    }

    override fun onShowPassword() {
        view?.setPasswordHelper(PASSWORD)
    }

}