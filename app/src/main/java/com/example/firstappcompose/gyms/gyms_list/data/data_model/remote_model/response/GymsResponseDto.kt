package com.example.firstappcompose.gyms.gyms_list.data.data_model.remote_model.response

import com.google.gson.annotations.SerializedName

data class GymsResponseDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("gym_location")
    val location: String,
    @SerializedName("gym_name")
    val name: String,
    @SerializedName("is_open")
    val gymStatus: Boolean,
)