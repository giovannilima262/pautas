package com.uds.pautas.util

import android.content.Context
import android.preference.PreferenceManager


object JwtUtils {

    val JWT = "jwt"

    fun get(context: Context) : String? {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val jwt = prefs.getString(JWT, null)
        return jwt
    }

    fun set(value: String, context: Context){
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = prefs.edit()
        editor.putString(JWT, value)
        editor.commit()
    }

    fun remove(context: Context){
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = prefs.edit()
        editor.remove(JWT)
        editor.commit()
    }

}