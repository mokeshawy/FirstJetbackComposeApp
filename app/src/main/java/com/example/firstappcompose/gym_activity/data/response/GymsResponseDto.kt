package com.example.firstappcompose.gym_activity.data.response

data class GymsResponseDto(
    val gym_location: String,
    val gym_name: String,
    val id: Int,
    val is_open: Boolean,
    var isFavorite: Boolean = false
)