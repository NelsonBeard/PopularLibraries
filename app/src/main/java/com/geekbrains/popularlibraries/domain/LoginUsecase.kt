package com.geekbrains.popularlibraries.domain

interface LoginUsecase {
    fun login(
        login: String,
        password: String,
        callback: (Boolean) -> Unit
    )
}