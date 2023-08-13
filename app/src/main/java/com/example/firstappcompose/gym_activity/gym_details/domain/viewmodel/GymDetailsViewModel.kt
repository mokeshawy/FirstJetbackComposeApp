package com.example.firstappcompose.gym_activity.gym_details.domain.viewmodel

import androidx.lifecycle.ViewModel
import com.example.firstappcompose.core.gyms_api_servecies.GymsApiServices
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GymDetailsViewModel : ViewModel() {

    private var gymsApiServices: GymsApiServices

    init {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jetbackcompose-default-rtdb.firebaseio.com/")
            .build()
        gymsApiServices = retrofit.create(GymsApiServices::class.java)

    }
}