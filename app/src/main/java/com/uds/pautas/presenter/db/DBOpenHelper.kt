package com.uds.pautas.presenter.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.uds.pautas.model.Schedule
import com.uds.pautas.presenter.db.repository.UserRepository
import com.uds.pautas.model.User
import com.uds.pautas.presenter.db.repository.ScheduleRepository

class DBOpenHelper(context: Context,
                   factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME,
        factory, DATABASE_VERSION), IDBOpenHelperPresenter {

    // Create
    private val createTableUser = "CREATE TABLE ${User.TABLE_NAME}(${User.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , ${User.COLUMN_EMAIL} TEXT UNIQUE NOT NULL, ${User.COLUMN_PASSWORD} TEXT NOT NULL, ${User.COLUMN_NAME} TEXT NOT NULL)"
    private val createTableSchedule = "CREATE TABLE ${Schedule.TABLE_NAME}(${Schedule.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ${Schedule.COLUMN_USER_ID} INTEGER NOT NULL, ${Schedule.COLUMN_TITLE} TEXT NOT NULL, ${Schedule.COLUMN_AUTHOR} TEXT NOT NULL, ${Schedule.COLUMN_DESCRIPTION} TEXT NOT NULL, ${Schedule.COLUMN_DETAIL} TEXT NOT NULL, ${Schedule.COLUMN_STATUS} TEXT NOT NULL, FOREIGN KEY(${Schedule.COLUMN_USER_ID}) REFERENCES ${User.TABLE_NAME}(${User.COLUMN_ID}))"

    //Drop
    private val dropTableUser = "DROP TABLE IF EXISTS ${User.TABLE_NAME}"
    private val dropTableSchedule = "DROP TABLE IF EXISTS ${Schedule.TABLE_NAME}"

    override fun repositoryUser(): UserRepository {
        return UserRepository(this)
    }

    override fun repositoryShedule(): ScheduleRepository {
        return ScheduleRepository(this)
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(createTableUser)
        db.execSQL(createTableSchedule)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL(dropTableUser)
        db.execSQL(dropTableSchedule)
        onCreate(db)
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "pauta.db"
    }
}