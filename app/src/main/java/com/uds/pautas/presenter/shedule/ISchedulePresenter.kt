package com.uds.pautas.presenter.shedule
import com.uds.pautas.model.Schedule
import com.uds.pautas.util.StatusEnum
import java.util.ArrayList

interface ISchedulePresenter {

    fun save(schedule: Schedule)
    fun findByUserStatus(userId: Int, statusEnum: StatusEnum): ArrayList<Schedule>
    fun changeStatus(id: Int, statusEnum: StatusEnum)

}