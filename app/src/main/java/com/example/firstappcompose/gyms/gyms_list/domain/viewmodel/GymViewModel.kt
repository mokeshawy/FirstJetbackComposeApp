package com.example.firstappcompose.gyms.gyms_list.domain.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstappcompose.gyms.gyms_list.domain.domain_model.GymsData
import com.example.firstappcompose.gyms.gyms_list.domain.domain_model.GymsScreenState
import com.example.firstappcompose.gyms.gyms_list.domain.usecase.GetInitialGymUseCase
import com.example.firstappcompose.gyms.gyms_list.domain.usecase.GetToggleFavoriteGymUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

const val GYM_IDS = "GYM_IDS"

@HiltViewModel
class GymViewModel @Inject constructor(
    private val getInitialGymUseCase: GetInitialGymUseCase,
    private val getToggleFavoriteGymUseCase : GetToggleFavoriteGymUseCase,
    private val stateHandle: SavedStateHandle
) : ViewModel() {

    private var _state by mutableStateOf(
        GymsScreenState(gyms = emptyList(), isLoading = true)
    )
    val state: State<GymsScreenState> get() = derivedStateOf { _state }

    private val job = Job()
    private val customCoroutine = CoroutineScope(context = job + Dispatchers.IO)


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


    fun handleFavoriteState(id: Int, oldVlue: Boolean) {
        val list = _state.gyms.toMutableList()
        val indexOf = list.indexOfFirst { it.id == id }
        //list[indexOf] = list[indexOf].copy(isFavorite = !list[indexOf].isFavorite)
        //storeSelectGym(list[indexOf])
        viewModelScope.launch {
            val updateGymsList = getToggleFavoriteGymUseCase(id, oldVlue)
            _state = _state.copy(gyms = updateGymsList, isLoading = false)
        }
    }


    private fun storeSelectGym(gymModel: GymsData) {
        val savedStateHandle = stateHandle.get<List<Int>?>(GYM_IDS).orEmpty().toMutableList()
        if (gymModel.isFavorite) savedStateHandle.add(gymModel.id)
        else savedStateHandle.remove(gymModel.id)
        stateHandle[GYM_IDS] = savedStateHandle
    }

    private fun List<GymsData>.retrieveGymIds(): List<GymsData> {
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