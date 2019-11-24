package com.uds.pautas.ui.main.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uds.pautas.R
import com.uds.pautas.di.component.DaggerLoginComponent
import com.uds.pautas.di.module.LoginModule
import com.uds.pautas.presenter.ILoginPresenter

import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject
import android.content.Intent
import com.uds.pautas.model.User
import com.uds.pautas.ui.main.activity.logged.LoggedActivity
import com.uds.pautas.util.JwtUtils
import com.uds.pautas.util.MesageToast.Companion.textErrorLogin
import com.uds.pautas.util.MesageToast.Companion.textRequired
import com.uds.pautas.util.MesageToast.Companion.toastShow

class LoginActivity : AppCompatActivity() {

    @Inject lateinit var presenter: ILoginPresenter

    private var buttonLoginClick = false
    private var buttonSingupClick = false
    private var buttonRememberPasswordClick = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        injectDependency()
        buttonLogin()
        buttonRememberPassword()
        buttonSingUp()
    }

    private fun buttonLogin() {
        login.setOnClickListener {
            if(buttonLoginClick) return@setOnClickListener

            buttonLoginClick = true
            val user = validateInputs()
            if(user.validLogin()) {
                if(presenter.login(user).validLogged()) {
                    buttonLogged(user.email)
                } else {
                    toastShow(this, textErrorLogin(resources))
                    buttonSingupClick = false
                }
            }
        }
    }

    private fun buttonLogged(email: String) {
        JwtUtils.set(email, this)
        val intent = Intent(this, LoggedActivity::class.java)
        startActivity(intent)
    }

    private fun buttonRememberPassword() {
        remember_password.setOnClickListener {
            if(buttonRememberPasswordClick) return@setOnClickListener
            buttonRememberPasswordClick = true
            val intent = Intent(this, RememberPasswordActivity::class.java)
            startActivity(intent)
        }
    }

    private  fun validateInputs(): User {
        val email = input_email.text.toString()
        val password = input_password.text.toString()
        val textToast = textToastValidate(email, password)
        toastShow(this, textRequired(resources, textToast))
        return User(email, password)
    }

    private fun textToastValidate(email: String, password: String): String {
        return when {
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

    private fun buttonSingUp() {
        singup.setOnClickListener {
            if(buttonSingupClick) return@setOnClickListener
            buttonSingupClick = true
            val intent = Intent(this, SingupActivity::class.java)
            startActivity(intent)
        }
    }

    private fun injectDependency() {
        val activityComponent = DaggerLoginComponent.builder()
            .loginModule(LoginModule(this))
            .build()
        activityComponent.inject(this)
    }

    override fun onResume() {
        super.onResume()
        buttonLoginClick = false
        buttonSingupClick = false
        buttonRememberPasswordClick = false
    }

}
