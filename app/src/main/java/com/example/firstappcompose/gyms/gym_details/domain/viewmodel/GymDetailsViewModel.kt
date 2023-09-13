package com.example.firstappcompose.gyms.gym_details.domain.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstappcompose.application.GymsApplication
import com.example.firstappcompose.core.gyms_api_servecies.GymsApiServices
import com.example.firstappcompose.core.room.room.GymDatabase
import com.example.firstappcompose.gyms.gyms_list.domain.domain_model.GymsData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GymDetailsViewModel(private val stateHandle: SavedStateHandle) : ViewModel() {

    var state by mutableStateOf<GymsData?>(null)
    private var apiServices: GymsApiServices

    private var gymDao = GymDatabase.getDaoInstance(GymsApplication.getApplicationContext())


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
            val gym = gymDao.getGymDetails(id)
            val gymData = GymsData(gym.id, gym.location, gym.name, gym.gymStatus, gym.isFavorite)
            state = gymData
        }
    }

    private suspend fun getGymFromRemoteById(id: Int) = withContext(Dispatchers.IO) {
        apiServices.getGymById(id).values.first()
    }
}