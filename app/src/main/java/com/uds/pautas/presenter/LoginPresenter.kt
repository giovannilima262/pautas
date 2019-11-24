package com.uds.pautas.presenter

import com.uds.pautas.model.User
import com.uds.pautas.presenter.db.DBOpenHelper

class LoginPresenter(private val dbOpenHelper: DBOpenHelper) : ILoginPresenter {

    override fun login(user: User): User {
        return dbOpenHelper.repositoryUser().getUserByNameAndPassword(user)
    }

    override fun singup(user: User) {
        dbOpenHelper.repositoryUser().insertUser(user)
    }

    override fun rememberPassword(email: String) {
        var password = dbOpenHelper.repositoryUser().getPasswordWithEmail(email)
        
    }

}