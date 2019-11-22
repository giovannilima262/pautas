package com.uds.pautas.di.module

import com.uds.pautas.BaseApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: BaseApp) {

    @Provides
    @Singleton
    fun provideApp() = app

}