package com.example.firstappcompose.gym_activity.domain.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.firstappcompose.core.gyms_api_servecies.GymsApiServices
import com.example.firstappcompose.gym_activity.data.response.GymsResponseDto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val GYM_IDS = "GYM_IDS"

class GymViewModel(private val stateHandle: SavedStateHandle) : ViewModel() {

    var state by mutableStateOf(emptyList<GymsResponseDto>())

    private var gymsApiServices: GymsApiServices

    init {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jetbackcompose-default-rtdb.firebaseio.com/")
            .build()
        gymsApiServices = retrofit.create(GymsApiServices::class.java)
    }

    fun getGymList() {
        gymsApiServices.getGyms().execute().body()?.let { gymsList ->
            state = gymsList.retrieveGymIds()
        }
    }

fun handleFavoriteState(id: Int) {
    val list = state.toMutableList()
    val indexOf = list.indexOfFirst { it.id == id }
    list[indexOf] = list[indexOf].copy(isFavorite = !list[indexOf].isFavorite)
    storeSelectGym(list[indexOf])
    state = list
}

private fun storeSelectGym(gymModel: GymsResponseDto) {
    val savedStateHandle = stateHandle.get<List<Int>?>(GYM_IDS).orEmpty().toMutableList()
    if (gymModel.isFavorite) savedStateHandle.add(gymModel.id)
    else savedStateHandle.remove(gymModel.id)
    stateHandle[GYM_IDS] = savedStateHandle
}

private fun List<GymsResponseDto>.retrieveGymIds(): List<GymsResponseDto> {
    stateHandle.get<List<Int>?>(GYM_IDS)?.let { ids ->
        ids.forEach { id ->
            this.find { it.id == id }?.isFavorite = true
        }
    }
    return this
}
}