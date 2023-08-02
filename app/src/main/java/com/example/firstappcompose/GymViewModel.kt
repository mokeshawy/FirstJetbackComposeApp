package com.example.firstappcompose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.listOfGym

class GymViewModel : ViewModel() {

    var state by mutableStateOf(getGymList())

    private fun getGymList() = listOfGym

    fun handleFavoriteState(id: Int) {
        val list = state.toMutableList()
        val indexOf = list.indexOfFirst { it.id == id }
        list[indexOf] = list[indexOf].copy(isFavorite = !list[indexOf].isFavorite)
        state = list
    }
}