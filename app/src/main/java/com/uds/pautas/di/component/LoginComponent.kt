package com.uds.pautas.di.component

import com.uds.pautas.di.module.LoginModule
import com.uds.pautas.ui.main.activity.LoginActivity
import dagger.Component

@Component(modules = [LoginModule::class])
interface LoginComponent {
    fun inject(loginActivity: LoginActivity)
}
