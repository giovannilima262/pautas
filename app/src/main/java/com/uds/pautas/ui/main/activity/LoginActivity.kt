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

class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var presenter: ILoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        injectDependency()
        buttonSingUp()
    }

    private fun buttonSingUp() {
        singup.setOnClickListener {
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

}
