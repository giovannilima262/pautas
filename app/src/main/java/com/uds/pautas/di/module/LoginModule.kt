package com.uds.pautas.di.module

import android.app.Activity
import com.uds.pautas.presenter.ILoginPresenter
import com.uds.pautas.presenter.LoginPresenter
import com.uds.pautas.presenter.db.DBOpenHelper
import dagger.Module
import dagger.Provides

@Module
class LoginModule(private var activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun providePresenter(): ILoginPresenter {
        return LoginPresenter(DBOpenHelper(activity, null))
    }

}