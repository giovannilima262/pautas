package com.uds.pautas.ui.main.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uds.pautas.R
import com.uds.pautas.di.component.DaggerLoginComponent
import com.uds.pautas.di.module.LoginModule
import com.uds.pautas.presenter.ILoginPresenter
import com.uds.pautas.util.MesageToast.Companion.textErrorSendEmail
import com.uds.pautas.util.MesageToast.Companion.textSuccessSendEmail
import com.uds.pautas.util.MesageToast.Companion.toastShow
import kotlinx.android.synthetic.main.activity_remember_password.*
import java.lang.Exception
import javax.inject.Inject

class RememberPasswordActivity : AppCompatActivity() {

    @Inject lateinit var presenter: ILoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remember_password)
        injectDependency()
        buttonSend()
        buttonBack()
    }

    private fun buttonSend() {
        button_send.setOnClickListener {
            try {
                presenter.rememberPassword(input_email.text.toString())
                toastShow(this, textSuccessSendEmail(resources))
                onBackPressed()
            } catch (e: Exception) {
                toastShow(this, textErrorSendEmail(resources))
            }
        }
    }

    private fun buttonBack() {
        back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun injectDependency() {
        val activityComponent = DaggerLoginComponent.builder()
            .loginModule(LoginModule(this))
            .build()
        activityComponent.inject(this)
    }
}
