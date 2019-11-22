package com.uds.pautas.di.component

import com.uds.pautas.BaseApp
import com.uds.pautas.di.module.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(app: BaseApp)
}