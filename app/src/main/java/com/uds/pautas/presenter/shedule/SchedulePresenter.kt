package com.uds.pautas.presenter.shedule

import com.uds.pautas.model.Schedule
import com.uds.pautas.presenter.db.DBOpenHelper
import com.uds.pautas.util.StatusEnum
import java.util.ArrayList

class SchedulePresenter(private val dbOpenHelper: DBOpenHelper) : ISchedulePresenter {
    
    override fun save(schedule: Schedule) {
        dbOpenHelper.repositoryShedule().insertShedule(schedule)
    }

    override fun findByUserStatus(userId: Int, statusEnum: StatusEnum): ArrayList<Schedule> {
        return dbOpenHelper.repositoryShedule().findByUserStatus(userId, statusEnum)
    }

    override fun changeStatus(id: Int, statusEnum: StatusEnum) {
        dbOpenHelper.repositoryShedule().changeStatus(id, statusEnum)
    }


}