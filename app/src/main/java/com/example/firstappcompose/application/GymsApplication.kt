package com.example.firstappcompose.application

import android.app.Application

class GymsApplication : Application() {

    init {
        gymsApplication = this
    }
    companion object{
        private lateinit var gymsApplication: GymsApplication
        fun getApplicationContext() = gymsApplication.applicationContext
    }
}