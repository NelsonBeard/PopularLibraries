package com.geekbrains.popularlibraries.domain

interface LoginApi {

    fun login(login: String, password: String): Boolean

    fun register(login: String, password: String, email: String): Boolean

    fun forgotPassword(login: String): Boolean
}