package com.example.firstappcompose.gyms.gym_details.domain.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstappcompose.core.gyms_api_servecies.GymsApiServices
import com.example.firstappcompose.core.room.room.GymsDAO
import com.example.firstappcompose.gyms.gyms_list.domain.domain_model.GymsData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GymDetailsViewModel @Inject constructor(
    private val apiServices: GymsApiServices,
    private val gymsDAO: GymsDAO,
    stateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf<GymsData?>(null)


    init {
        val id = stateHandle.get<Int>("gym_id") ?: 0
        getGym(id)
    }

    private fun getGym(id: Int) {
        viewModelScope.launch {
            val gym = gymsDAO.getGymDetails(id)
            val gymData = GymsData(gym.id, gym.location, gym.name, gym.gymStatus, gym.isFavorite)
            state = gymData
        }
    }

    private suspend fun getGymFromRemoteById(id: Int) = withContext(Dispatchers.IO) {
        apiServices.getGymById(id).values.first()
    }
}