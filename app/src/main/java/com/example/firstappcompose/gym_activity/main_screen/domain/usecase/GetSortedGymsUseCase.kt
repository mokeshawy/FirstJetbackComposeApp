package com.example.firstappcompose.gym_activity.main_screen.domain.usecase

import com.example.firstappcompose.gym_activity.main_screen.data.repository.GetAllGymsRepositoryImpl

class GetSortedGymsUseCase {

    private val getAllGymRepositoryImpl = GetAllGymsRepositoryImpl()
    suspend operator fun invoke() = getAllGymRepositoryImpl.getAllGymsList().sortedBy { it.name }
}