package com.example.firstappcompose.gyms.gyms_list.domain.usecase

import com.example.firstappcompose.gyms.gyms_list.data.repository.GetToggleFavoriteGymRepositoryImpl
import com.example.firstappcompose.gyms.gyms_list.domain.domain_model.GymsData
import javax.inject.Inject

class GetToggleFavoriteGymUseCase @Inject constructor(private val getSortedGymsUseCase : GetSortedGymsUseCase){


    private val getToggleFavoriteGymRepositoryImpl = GetToggleFavoriteGymRepositoryImpl()

    suspend operator fun invoke(gymId: Int, oldValue: Boolean): List<GymsData> {
        val newValue = oldValue.not()
        getToggleFavoriteGymRepositoryImpl.toggleFavoriteGym(gymId, newValue)
        return getSortedGymsUseCase()
    }
}