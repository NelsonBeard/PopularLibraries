package com.geekbrains.popularlibraries.data

import com.geekbrains.popularlibraries.domain.LoginApi

val PASSWORD = "123456"
var errorMessage: String = ""

class MockLoginApiImpl : LoginApi {
    override fun login(login: String, password: String): Boolean {
        Thread.sleep(1000)
        if (login == "") {
            errorMessage = "Введите логин"
            return false
        } else if (password == PASSWORD) {
            return true
        }
        errorMessage = "Неверный пароль"
        return false
    }

    override fun register(login: String, password: String, email: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun forgotPassword(login: String): Boolean {
        TODO("Not yet implemented")
    }
}