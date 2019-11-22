package com.uds.pautas.di.component

import android.app.Activity
import com.uds.pautas.BaseApp
import com.uds.pautas.db.DBOpenHelper
import com.uds.pautas.di.module.AppModule
import com.uds.pautas.di.module.DBOpenHelperModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DBOpenHelperModule::class])
interface DBOpenHelperComponent {
    fun inject(activity: Activity)
}