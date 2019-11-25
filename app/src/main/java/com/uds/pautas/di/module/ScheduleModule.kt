package com.uds.pautas.di.module

import android.app.Activity
import com.uds.pautas.presenter.db.DBOpenHelper
import com.uds.pautas.presenter.shedule.ISchedulePresenter
import com.uds.pautas.presenter.shedule.SchedulePresenter
import dagger.Module
import dagger.Provides

@Module
class ScheduleModule(private var activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun providePresenter(): ISchedulePresenter {
        return SchedulePresenter(DBOpenHelper(activity, null))
    }

}