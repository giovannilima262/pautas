package com.uds.pautas.ui.main.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uds.pautas.R
import com.uds.pautas.di.component.DaggerLoginComponent
import com.uds.pautas.di.module.LoginModule
import com.uds.pautas.model.User
import com.uds.pautas.presenter.ILoginPresenter
import com.uds.pautas.util.MesageToast.Companion.textErrorSingup
import com.uds.pautas.util.MesageToast.Companion.textRequired
import com.uds.pautas.util.MesageToast.Companion.textSuccessSingup
import com.uds.pautas.util.MesageToast.Companion.toastShow
import kotlinx.android.synthetic.main.activity_sing_up.*
import java.lang.Exception
import javax.inject.Inject

class SingupActivity : AppCompatActivity() {

    @Inject lateinit var presenter: ILoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up)
        injectDependency()
        singupUser()
        buttonBack()
    }

    private fun singupUser() {
        button_singup.setOnClickListener {
            val user = validateInputs()
            if(user.validSingup()) {
                try {
                    presenter.singup(user)
                    toastShow(this, textSuccessSingup(resources))
                    onBackPressed()
                } catch (e: Exception) {
                    toastShow(this, textErrorSingup(resources))
                }
            }
        }
    }

    private fun validateInputs(): User {
        val name = input_name.text.toString()
        val email = input_email.text.toString()
        val password = input_password.text.toString()
        val textToast = textToastValidate(name, email, password)
        toastShow(this, textRequired(resources, textToast))
        return User(name, email, password)
    }

    private fun textToastValidate(name: String, email: String, password: String): String {
        return when {
            name.isEmpty() -> {
                input_name.hint
            }
            email.isEmpty() -> {
                input_email.hint
            }
            password.isEmpty() -> {
                input_password.hint
            } else -> {
                ""
            }
        } as String
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
