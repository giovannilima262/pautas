package com.uds.pautas

import android.app.Application
import com.uds.pautas.di.component.AppComponent
import com.uds.pautas.di.component.DaggerAppComponent
import com.uds.pautas.di.module.AppModule

class BaseApp: Application() {

    private val component: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}