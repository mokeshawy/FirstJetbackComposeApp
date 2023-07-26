package com.example.firstappcompose

import androidx.lifecycle.ViewModel
import com.example.listOfGym

class GymViewModel : ViewModel() {

    fun getGymList() = listOfGym
}