package com.example.firstappcompose.gyms.gyms_list.domain.repository

interface GetToggleFavoriteGymRepository {

    suspend fun toggleFavoriteGym(gymId: Int, state: Boolean)
}