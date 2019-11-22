package com.uds.pautas.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.uds.pautas.db.repository.UserRepository
import com.uds.pautas.model.User

class DBOpenHelper(context: Context,
                   factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME,
        factory, DATABASE_VERSION) {

    // Create
    private val createTableUser = "CREATE TABLE ${User.TABLE_NAME}(${User.COLUMN_ID} INTEGER PRIMARY KEY, ${User.COLUMN_EMAIL} TEXT, ${User.COLUMN_PASSWORD} TEXT)"

    //Drop
    private val dropTableUser = "DROP TABLE IF EXISTS ${User.TABLE_NAME}"

    fun repositoryUser(): UserRepository {
        return UserRepository(this)
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(createTableUser)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL(dropTableUser)
        onCreate(db)
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "pauta.db"
    }
}