package com.example.firstappcompose.gym_activity.main_screen.domain.usecase

import com.example.firstappcompose.gym_activity.main_screen.data.repository.GetAllGymsRepositoryImpl
import com.example.firstappcompose.gym_activity.main_screen.data.response.GymsResponseDto

class GetInitialGymUseCase {

    private val getAllGymsRepositoryImpl = GetAllGymsRepositoryImpl()
    private val getSortedGymsUseCase = GetSortedGymsUseCase()

    suspend operator fun invoke(): List<GymsResponseDto> {
        getAllGymsRepositoryImpl.loadGymsList()
        return getSortedGymsUseCase()
    }
}