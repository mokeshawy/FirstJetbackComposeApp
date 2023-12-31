package com.example.firstappcompose.core.gyms_api_servecies

import com.example.firstappcompose.gyms.gyms_list.data.data_model.remote_model.response.GymsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GymsApiServices {

    @GET("gyms.json")
    suspend fun getGyms(): List<GymsResponseDto>

    @GET("gyms.json?orderBy=\"id\"")
    suspend fun getGymById(@Query("equalTo") id: Int): Map<String, GymsResponseDto>
}