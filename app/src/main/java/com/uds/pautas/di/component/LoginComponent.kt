package com.uds.pautas.di.component

import android.app.Activity
import com.uds.pautas.di.module.LoginModule
import com.uds.pautas.ui.main.activity.LoginActivity
import com.uds.pautas.ui.main.activity.RememberPasswordActivity
import com.uds.pautas.ui.main.activity.SingupActivity
import dagger.Component

@Component(modules = [LoginModule::class])
interface LoginComponent {

    fun inject(singupActivity: SingupActivity)

    fun inject(loginActivity: LoginActivity)

    fun inject(rememberPasswordActivity: RememberPasswordActivity)
}
