package com.example.firstappcompose.gym_activity.main_screen.domain.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstappcompose.gym_activity.main_screen.data.response.GymsResponseDto
import com.example.firstappcompose.gym_activity.main_screen.domain.model.GymsScreenState
import com.example.firstappcompose.gym_activity.main_screen.domain.usecase.GetInitialGymUseCase
import com.example.firstappcompose.gym_activity.main_screen.domain.usecase.GetToggleFavoriteGymUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val GYM_IDS = "GYM_IDS"

class GymViewModel(private val stateHandle: SavedStateHandle) : ViewModel() {

    private var _state by mutableStateOf(
        GymsScreenState(gyms = emptyList(), isLoading = true)
    )
    val state: State<GymsScreenState> get() = derivedStateOf { _state }

    private val job = Job()
    private val customCoroutine = CoroutineScope(context = job + Dispatchers.IO)

    private val getInitialGymUseCase = GetInitialGymUseCase()

    private val getToggleFavoriteGymUseCase = GetToggleFavoriteGymUseCase()

    private val errorHandler = CoroutineExceptionHandler { _, th ->
        th.printStackTrace()
        _state = _state.copy(isLoading = false, errorMessage = th.message)
    }

    init {
        getGymList()
    }

    private fun getGymList() {
        customCoroutine.launch(errorHandler) {
            withContext(Dispatchers.Main) {
                val gyms = getInitialGymUseCase()
                _state = _state.copy(gyms = gyms, isLoading = false)
            }
        }
    }


    fun handleFavoriteState(id: Int) {
        val list = _state.gyms.toMutableList()
        val indexOf = list.indexOfFirst { it.id == id }
        //list[indexOf] = list[indexOf].copy(isFavorite = !list[indexOf].isFavorite)
        //storeSelectGym(list[indexOf])
        viewModelScope.launch {
            val updateGymsList = getToggleFavoriteGymUseCase(id, list[indexOf].isFavorite)
            _state = _state.copy(gyms = updateGymsList, isLoading = false)
        }
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