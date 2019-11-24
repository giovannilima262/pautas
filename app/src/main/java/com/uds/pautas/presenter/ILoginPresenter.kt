package com.uds.pautas.presenter

import com.uds.pautas.model.User

interface ILoginPresenter {
    fun login(user: User): User
    fun singup(user: User)
    fun rememberPassword(email: String)
}