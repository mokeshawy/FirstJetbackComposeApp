package com.example.firstappcompose.gym_activity.main_screen.data.response

import com.google.gson.annotations.SerializedName

data class GymsResponseDto(
    @SerializedName("gym_location")
    val gym_location: String,
    @SerializedName("gym_name")
    val gym_name: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_open")
    val is_open: Boolean,
    var isFavorite: Boolean = false
)