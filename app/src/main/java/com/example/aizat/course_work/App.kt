package com.example.aizat.course_work

import android.annotation.SuppressLint
import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var instance: App
            private set
    }
}