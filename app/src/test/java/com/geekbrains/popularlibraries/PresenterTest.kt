package com.geekbrains.popularlibraries

import com.geekbrains.popularlibraries.data.MockLoginApiImpl
import com.geekbrains.popularlibraries.ui.login.Contract
import com.geekbrains.popularlibraries.ui.login.Presenter
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class PresenterTest {

    private lateinit var presenter: Presenter

    @Mock
    private lateinit var loginUsecase: MockLoginApiImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        presenter = Presenter(loginUsecase)


    }

    @Test
    fun onLogin_Test() {
        val login = "someLogin"
        val password = "123456"

        presenter.onLogin(login, password)
        verify(loginUsecase).login(login, password)
    }
}