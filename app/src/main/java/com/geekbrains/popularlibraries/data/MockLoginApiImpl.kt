package com.geekbrains.popularlibraries.data

import com.geekbrains.popularlibraries.domain.LoginApi

const val PASSWORD = "123456"
var errorText: String = ""

class MockLoginApiImpl : LoginApi {
    override fun login(login: String, password: String): Boolean {
        if (login == "") {
            errorText = "Введите логин"
            return false
        } else if (password == PASSWORD) {
            return true
        }
        errorText = "Неверный пароль"
        return false
    }

    override fun register(login: String, password: String, email: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun forgotPassword(login: String): Boolean {
        TODO("Not yet implemented")
    }
}