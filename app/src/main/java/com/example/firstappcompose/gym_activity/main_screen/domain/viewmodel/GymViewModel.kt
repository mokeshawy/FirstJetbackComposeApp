package com.example.firstappcompose.gym_activity.main_screen.domain.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstappcompose.application.GymsApplication.Companion.getApplicationContext
import com.example.firstappcompose.core.gyms_api_servecies.GymsApiServices
import com.example.firstappcompose.core.room.room.GymDatabase
import com.example.firstappcompose.gym_activity.main_screen.data.response.GymsResponseDto
import com.example.firstappcompose.gym_activity.main_screen.domain.model.GymFavoriteState
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
                state = getAllGyms()
            }
        }
    }

    private suspend fun getAllGyms() = withContext(Dispatchers.IO) {
        try {
            updateLocalDatabase()
        } catch (e: Exception) {
            if (gymDao.getAll().isEmpty())
                throw Exception("Something wrong. No data was found, try connect to internet")
        }
        gymDao.getAll()
    }

    private suspend fun updateLocalDatabase() {
        val gyms = gymsApiServices.getGyms()
        val favoriteGymsList = gymDao.getFavoriteGyms()
        gymDao.addAll(gyms)
        gymDao.updateAll(
            favoriteGymsList.map {
                GymFavoriteState(id = it.id, true)
            }
        )
    }

    fun handleFavoriteState(id: Int) {
        val list = state.toMutableList()
        val indexOf = list.indexOfFirst { it.id == id }
        //list[indexOf] = list[indexOf].copy(isFavorite = !list[indexOf].isFavorite)
        //storeSelectGym(list[indexOf])
        viewModelScope.launch {
            val updateGymsList = toggleFavoriteGym(id, !list[indexOf].isFavorite)
            state = updateGymsList
        }
    }

    private suspend fun toggleFavoriteGym(gymId: Int, currentFavoriteState: Boolean) =
        withContext(Dispatchers.IO) {
            gymDao.update(GymFavoriteState(gymId, currentFavoriteState))
            return@withContext gymDao.getAll()
        }

    private fun storeSelectGym(gymModel: GymsResponseDto) {
        val savedStateHandle = stateHandle.get<List<Int>?>(GYM_IDS).orEmpty().toMutableList()
        if (gymModel.isFavorite) savedStateHandle.add(gymModel.id)
        else savedStateHandle.remove(gymModel.id)
        stateHandle[GYM_IDS] = savedStateHandle
    }

    private fun List<GymsResponseDto>.retrieveGymIds(): List<GymsResponseDto> {
        stateHandle.get<List<Int>?>(GYM_IDS)?.let { ids ->
            val gymsMap = this.associateBy { it.id }.toMutableMap()
            ids.forEach { id ->
                val gym = gymsMap[id] ?: return@forEach
                gymsMap[id] = gym.copy(isFavorite = true)
            }
            return gymsMap.values.toList()
        }
        return this
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}