package com.example.firstappcompose.gym_activity.main_screen.domain.usecase

import com.example.firstappcompose.gym_activity.main_screen.data.repository.GetToggleFavoriteGymRepositoryImpl
import com.example.firstappcompose.gym_activity.main_screen.data.response.GymsResponseDto

class GetToggleFavoriteGymUseCase {


    private val getToggleFavoriteGymRepositoryImpl = GetToggleFavoriteGymRepositoryImpl()
    private val getSortedGymsUseCase = GetSortedGymsUseCase()

    suspend operator fun invoke(gymId: Int, oldValue: Boolean): List<GymsResponseDto> {
        val newValue = oldValue.not()
        getToggleFavoriteGymRepositoryImpl.toggleFavoriteGym(gymId, newValue)
        return getSortedGymsUseCase()
    }
}