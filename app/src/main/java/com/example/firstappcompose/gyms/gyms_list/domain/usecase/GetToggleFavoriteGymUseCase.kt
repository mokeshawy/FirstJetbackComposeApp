package com.example.firstappcompose.gyms.gyms_list.domain.usecase

import com.example.firstappcompose.gyms.gyms_list.data.repository.GetToggleFavoriteGymRepositoryImpl
import com.example.firstappcompose.gyms.gyms_list.domain.domain_model.GymsData

class GetToggleFavoriteGymUseCase {


    private val getToggleFavoriteGymRepositoryImpl = GetToggleFavoriteGymRepositoryImpl()
    private val getSortedGymsUseCase = GetSortedGymsUseCase()

    suspend operator fun invoke(gymId: Int, oldValue: Boolean): List<GymsData> {
        val newValue = oldValue.not()
        getToggleFavoriteGymRepositoryImpl.toggleFavoriteGym(gymId, newValue)
        return getSortedGymsUseCase()
    }
}