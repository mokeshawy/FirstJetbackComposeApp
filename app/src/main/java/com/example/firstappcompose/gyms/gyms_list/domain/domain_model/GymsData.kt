package com.example.firstappcompose.gyms.gyms_list.domain.domain_model

data class GymsData(
    val id: Int,
    val location: String,
    val name: String,
    val gymStatus: Boolean,
    val isFavorite: Boolean = false
)