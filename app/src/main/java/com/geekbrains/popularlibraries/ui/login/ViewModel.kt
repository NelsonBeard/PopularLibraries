package com.geekbrains.popularlibraries.ui.login

import com.geekbrains.popularlibraries.data.errorMessage
import com.geekbrains.popularlibraries.domain.LoginUsecase
import com.geekbrains.popularlibraries.utils.Publisher

class ViewModel(
    private val loginUsecase: LoginUsecase
) : Contract.ViewModel {
    override val shouldShowProgress: Publisher<Boolean> = Publisher()
    override val isSuccess: Publisher<Boolean> = Publisher()
    override val errorText: Publisher<String?> = Publisher()
    override val registration: Publisher<Boolean> = Publisher()
    override val helper: Publisher<Boolean> = Publisher()

    override fun onLogin(login: String, password: String) {
        shouldShowProgress.post(true)

            loginUsecase.login(login, password) { result ->
                shouldShowProgress.post(false)
                if (result) {
                    isSuccess.post(true)
                } else {
                    errorText.post(errorMessage)
                    isSuccess.post(false)
                }
            }

    }

    override fun onRegistration() {
        registration.post(true)

    }

    override fun onShowPassword() {
        helper.post(true)
    }
}