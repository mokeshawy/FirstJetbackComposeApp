package com.example.firstappcompose.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GymsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

    }

    init {
        gymsApplication = this
    }
    companion object{
        private lateinit var gymsApplication: GymsApplication
        fun getApplicationContext() = gymsApplication.applicationContext
    }
}