package com.example.firstappcompose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.GymModel
import com.example.listOfGym

const val GYM_IDS = "GYM_IDS"

class GymViewModel(private val stateHandle: SavedStateHandle) : ViewModel() {

    var state by mutableStateOf(retrieveGymIds())

    private fun getGymList() = listOfGym

    fun handleFavoriteState(id: Int) {
        val list = state.toMutableList()
        val indexOf = list.indexOfFirst { it.id == id }
        list[indexOf] = list[indexOf].copy(isFavorite = !list[indexOf].isFavorite)
        storeSelectGym(list[indexOf])
        state = list
    }

    private fun storeSelectGym(gymModel: GymModel) {
        val savedStateHandle = stateHandle.get<List<Int>?>(GYM_IDS).orEmpty().toMutableList()
        if (gymModel.isFavorite) savedStateHandle.add(gymModel.id)
        else savedStateHandle.remove(gymModel.id)
        stateHandle[GYM_IDS] = savedStateHandle
    }

    private fun retrieveGymIds(): List<GymModel> {
        val gym = getGymList()
        stateHandle.get<List<Int>?>(GYM_IDS)?.let { ids ->
            ids.forEach { id ->
                gym.find { it.id == id }?.isFavorite = true
            }
        }
        return gym
    }
}