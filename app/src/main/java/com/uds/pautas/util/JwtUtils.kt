package com.uds.pautas.util

import android.content.Context
import android.preference.PreferenceManager


object JwtUtils {

    const val JWT = "jwt"
    const val USER_NAME = "user"

    fun getJwt(context: Context) : Int? {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return  prefs.getInt(JWT, 0)
    }

    fun getUserName(context: Context) : String? {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return  prefs.getString(USER_NAME, null)
    }

    fun set(jwt: Int?, userName: String, context: Context){
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = prefs.edit()
        editor.putInt(JWT, jwt!!)
        editor.putString(USER_NAME, userName)
        editor.apply()
    }

    fun remove(context: Context){
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = prefs.edit()
        editor.remove(JWT)
        editor.remove(USER_NAME)
        editor.apply()
    }

}