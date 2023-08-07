package com.example.firstappcompose.gym_activity.domain.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.firstappcompose.core.gyms_api_servecies.GymsApiServices
import com.example.firstappcompose.gym_activity.data.response.GymsResponseDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val GYM_IDS = "GYM_IDS"

class GymViewModel(private val stateHandle: SavedStateHandle) : ViewModel() {

    var state by mutableStateOf(emptyList<GymsResponseDto>())

    private var gymsApiServices: GymsApiServices
    lateinit var GymsResponseDtoCall: Call<List<GymsResponseDto>>

    init {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jetbackcompose-default-rtdb.firebaseio.com/")
            .build()
        gymsApiServices = retrofit.create(GymsApiServices::class.java)

        getGymList()
    }

    private fun getGymList() {
        GymsResponseDtoCall = gymsApiServices.getGyms()
        GymsResponseDtoCall.enqueue(object : Callback<List<GymsResponseDto>> {
            override fun onResponse(
                call: Call<List<GymsResponseDto>>, response: Response<List<GymsResponseDto>>
            ) {
                response.body()?.let { gymsList ->
                    state = gymsList.retrieveGymIds()
                }
            }

            override fun onFailure(call: Call<List<GymsResponseDto>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

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

    override fun onCleared() {
        super.onCleared()
        GymsResponseDtoCall.cancel()
    }
}