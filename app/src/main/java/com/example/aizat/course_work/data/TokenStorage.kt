package com.example.aizat.course_work.data

import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.aizat.course_work.App

object TokenStorage {

    private val preferences: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(App.instance)
    }

    fun getToken(): String? {
        return preferences.getString("token", null)
    }

    fun putToken(token: String) {
        preferences.edit().putString("token", token).apply()
    }

    fun removeToken() {
        preferences.edit().remove("token").apply()
    }
}