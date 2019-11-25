package com.uds.pautas.di.component

import com.uds.pautas.di.module.ScheduleModule
import com.uds.pautas.ui.main.activity.logged.AddSheduleActivity
import com.uds.pautas.ui.main.activity.logged.PlaceholderFragment
import dagger.Component

@Component(modules = [ScheduleModule::class])
interface ScheduleComponent {

    fun inject(sheduleActivity: AddSheduleActivity)

    fun inject(placeholderFragment: PlaceholderFragment)

}
