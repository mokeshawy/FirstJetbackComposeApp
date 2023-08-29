package com.example.firstappcompose.gym_activity.main_screen.domain.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.firstappcompose.application.GymsApplication.Companion.getApplicationContext
import com.example.firstappcompose.core.gyms_api_servecies.GymsApiServices
import com.example.firstappcompose.gym_activity.main_screen.data.response.GymsResponseDto
import com.example.firstappcompose.gym_activity.main_screen.domain.room.GymDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val GYM_IDS = "GYM_IDS"

class GymViewModel(private val stateHandle: SavedStateHandle) : ViewModel() {

    var state by mutableStateOf(emptyList<GymsResponseDto>())

    private var gymsApiServices: GymsApiServices
    private val job = Job()
    private val customCoroutine = CoroutineScope(context = job + Dispatchers.IO)

    private var gymDao = GymDatabase.getDaoInstance(getApplicationContext())

    init {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jetbackcompose-default-rtdb.firebaseio.com/")
            .build()
        gymsApiServices = retrofit.create(GymsApiServices::class.java)

        getGymList()
    }

    private fun getGymList() {
        customCoroutine.launch {
            withContext(Dispatchers.Main) {
                state = getAllGyms().retrieveGymIds()
            }
        }
    }

    private suspend fun getAllGyms() = withContext(Dispatchers.IO) {
        try {
            val gyms = gymsApiServices.getGyms()
            gymDao.addAll(gyms)
            return@withContext gyms
        } catch (e: Exception) {
            gymDao.getAll()
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

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}