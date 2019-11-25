package com.uds.pautas.presenter.db.repository

import android.content.ContentValues
import com.uds.pautas.presenter.db.DBOpenHelper
import com.uds.pautas.model.Schedule
import com.uds.pautas.util.StatusEnum

class ScheduleRepository(private val dbOpenHelper: DBOpenHelper) {

    fun insertShedule(schedule: Schedule) {
        val values = ContentValues()
        values.put(Schedule.COLUMN_AUTHOR, schedule.author)
        values.put(Schedule.COLUMN_DESCRIPTION, schedule.description)
        values.put(Schedule.COLUMN_DETAIL, schedule.detail)
        values.put(Schedule.COLUMN_TITLE, schedule.title)
        values.put(Schedule.COLUMN_STATUS, schedule.status.name)
        values.put(Schedule.COLUMN_USER_ID, schedule.user!!.id)
        val db = dbOpenHelper.writableDatabase
        db.insert(Schedule.TABLE_NAME, null, values)
        db.close()
    }

    fun findByUserStatus(userId: Int, statusEnum: StatusEnum): ArrayList<Schedule> {
        var scheduleArray: ArrayList<Schedule> = arrayListOf()
        var schedule: Schedule
        val db = dbOpenHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM ${Schedule.TABLE_NAME} WHERE ${Schedule.COLUMN_USER_ID} = ? AND ${Schedule.COLUMN_STATUS} = ?",
            arrayOf(userId.toString(), statusEnum.name))
        if (cursor != null) {
            while (cursor.moveToNext()) {
                schedule = Schedule()
                schedule.id = cursor.getInt(cursor.getColumnIndex(Schedule.COLUMN_ID))
                schedule.status = StatusEnum.valueOf(cursor.getString(cursor.getColumnIndex(Schedule.COLUMN_STATUS)))
                schedule.author = cursor.getString(cursor.getColumnIndex(Schedule.COLUMN_AUTHOR))
                schedule.description = cursor.getString(cursor.getColumnIndex(Schedule.COLUMN_DESCRIPTION))
                schedule.detail = cursor.getString(cursor.getColumnIndex(Schedule.COLUMN_DETAIL))
                schedule.title = cursor.getString(cursor.getColumnIndex(Schedule.COLUMN_TITLE))
                scheduleArray.add(schedule)
            }
        }
        cursor.close()
        return scheduleArray
    }

    fun changeStatus(id: Int, statusEnum: StatusEnum) {
        val values = ContentValues()
        values.put(Schedule.COLUMN_STATUS, statusEnum.name)
        val db = dbOpenHelper.writableDatabase
        db.update(Schedule.TABLE_NAME, values, "${Schedule.COLUMN_ID}=$id", null)
        db.close()
    }

}