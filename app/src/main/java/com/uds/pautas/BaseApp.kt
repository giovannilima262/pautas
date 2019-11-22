package com.uds.pautas

import android.app.Application
import com.uds.pautas.di.component.AppComponent
import com.uds.pautas.di.component.DaggerAppComponent

class BaseApp: Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}