package com.uds.pautas.presenter

import android.util.Log
import co.nedim.maildroidx.MaildroidX
import co.nedim.maildroidx.MaildroidXType
import com.uds.pautas.model.User
import com.uds.pautas.presenter.db.DBOpenHelper
import com.uds.pautas.presenter.mail.Credentials

class LoginPresenter(private val dbOpenHelper: DBOpenHelper) : ILoginPresenter {

    override fun login(user: User): User {
        return dbOpenHelper.repositoryUser().findUserByNameAndPassword(user)
    }

    override fun singup(user: User) {
        dbOpenHelper.repositoryUser().insertUser(user)
    }

    override fun rememberPassword(email: String) {
        val emailText = email.trim()
        val password = dbOpenHelper.repositoryUser().findtPasswordWithEmail(emailText)
        if(password.isEmpty()) {
            throw Exception()
        }
        send(password, emailText)
    }

    private fun send(password: String, email: String) {
        MaildroidX.Builder()
            .smtp("smtp.gmail.com")
            .smtpUsername(Credentials.EMAIL)
            .smtpPassword(Credentials.PASSWORD)
            .smtpAuthentication(true)
            .port("587")
            .type(MaildroidXType.HTML)
            .to(email)
            .from(Credentials.EMAIL)
            .subject("Senha")
            .body(password)
            .onCompleteCallback(object : MaildroidX.onCompleteCallback{
                override val timeout: Long = 3000
                override fun onSuccess() {
                    Log.d("MaildroidX",  "SUCCESS")
                }
                override fun onFail(errorMessage: String) {
                    Log.d("MaildroidX",  "FAIL")
                }
            })
            .mail()
    }


}