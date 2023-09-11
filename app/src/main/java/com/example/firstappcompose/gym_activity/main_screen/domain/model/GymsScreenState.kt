package com.example.firstappcompose.gym_activity.main_screen.domain.model

import com.example.firstappcompose.gym_activity.main_screen.data.response.GymsResponseDto

data class GymsScreenState(
    val gyms: List<GymsResponseDto>,
    val isLoading: Boolean,
    val errorMessage : String? = null
)