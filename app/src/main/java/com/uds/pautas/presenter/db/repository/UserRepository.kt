package com.uds.pautas.presenter.db.repository

import android.content.ContentValues
import com.uds.pautas.presenter.db.DBOpenHelper
import com.uds.pautas.model.User

class UserRepository(private val dbOpenHelper: DBOpenHelper) {

    fun insertUser(user: User) {
        val values = ContentValues()
        values.put(User.COLUMN_EMAIL, user.email)
        values.put(User.COLUMN_PASSWORD, user.password)
        val db = dbOpenHelper.writableDatabase
        db.insert(User.TABLE_NAME, null, values)
        db.close()
    }

    fun getUserByNameAndPassword(userFilter: User): User {
        val user = User()
        val db = dbOpenHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM ${User.TABLE_NAME} WHERE ${User.COLUMN_EMAIL} = ? AND ${User.COLUMN_PASSWORD} = ?",
            arrayOf(userFilter.email, userFilter.password))
        if (cursor != null) {
            while (cursor.moveToNext()) {
                user.id = cursor.getInt(cursor.getColumnIndex(User.COLUMN_ID))
                user.email = cursor.getString(cursor.getColumnIndex(User.COLUMN_EMAIL))
                user.password = cursor.getString(cursor.getColumnIndex(User.COLUMN_PASSWORD))
            }
        }
        cursor.close()
        return user
    }

    fun getPasswordWithEmail(email: String): String {
        var password = ""
        val db = dbOpenHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM ${User.TABLE_NAME} WHERE ${User.COLUMN_EMAIL} = ? ",
            arrayOf(email))
        if (cursor != null) {
            while (cursor.moveToNext()) {
                password = cursor.getString(cursor.getColumnIndex(User.COLUMN_PASSWORD))
            }
        }
        cursor.close()
        return password
    }

}