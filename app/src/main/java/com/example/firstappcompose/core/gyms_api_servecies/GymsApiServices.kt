package com.example.firstappcompose.core.gyms_api_servecies

import com.example.firstappcompose.gym_activity.data.response.GymsResponseDto
import retrofit2.Call
import retrofit2.http.GET

interface GymsApiServices {

    @GET("gyms.json")
    fun getGyms() : Call<List<GymsResponseDto>>
}