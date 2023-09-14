package com.example.firstappcompose.gyms.gyms_list.domain.usecase

import com.example.firstappcompose.gyms.gyms_list.data.repository.GetAllGymsRepositoryImpl
import com.example.firstappcompose.gyms.gyms_list.domain.domain_model.GymsData
import javax.inject.Inject

class GetInitialGymUseCase @Inject constructor(
    private val getSortedGymsUseCase : GetSortedGymsUseCase,
    private val getAllGymsRepositoryImpl: GetAllGymsRepositoryImpl
) {


    suspend operator fun invoke(): List<GymsData> {
        getAllGymsRepositoryImpl.loadGymsList()
        return getSortedGymsUseCase()
    }
}