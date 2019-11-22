package com.uds.pautas.di.module

import android.app.Activity
import com.uds.pautas.db.DBOpenHelper
import com.uds.pautas.presenter.ILoginPresenter
import com.uds.pautas.presenter.LoginPresenter
import dagger.Module
import dagger.Provides

@Module
class DBOpenHelperModule(private var activity: Activity) {

    @Provides
    fun provide(): DBOpenHelper {
        return DBOpenHelper(activity, null)
    }

}