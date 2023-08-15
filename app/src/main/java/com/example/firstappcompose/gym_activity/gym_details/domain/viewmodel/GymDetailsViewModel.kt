package com.example.firstappcompose.gym_activity.gym_details.domain.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstappcompose.core.gyms_api_servecies.GymsApiServices
import com.example.firstappcompose.gym_activity.main_screen.data.response.GymsResponseDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GymDetailsViewModel(private val stateHandle: SavedStateHandle) : ViewModel() {

    var state by mutableStateOf<GymsResponseDto?>(null)
    private var apiServices: GymsApiServices

    init {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jetbackcompose-default-rtdb.firebaseio.com/")
            .build()
        apiServices = retrofit.create(GymsApiServices::class.java)
        val id = stateHandle.get<Int>("gym_id") ?: 0
        getGym(id)
    }

    private fun getGym(id: Int) {
        viewModelScope.launch {
            val gym = getGymFromRemoteById(id)
            state = gym
        }
    }

    private suspend fun getGymFromRemoteById(id: Int) = withContext(Dispatchers.IO) {
        apiServices.getGymById(id).values.first()
    }
}