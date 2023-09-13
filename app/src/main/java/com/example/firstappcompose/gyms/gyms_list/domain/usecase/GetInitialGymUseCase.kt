package com.example.firstappcompose.gyms.gyms_list.domain.usecase

import com.example.firstappcompose.gyms.gyms_list.data.repository.GetAllGymsRepositoryImpl
import com.example.firstappcompose.gyms.gyms_list.domain.domain_model.GymsData

class GetInitialGymUseCase {

    private val getAllGymsRepositoryImpl = GetAllGymsRepositoryImpl()
    private val getSortedGymsUseCase = GetSortedGymsUseCase()

    suspend operator fun invoke(): List<GymsData> {
        getAllGymsRepositoryImpl.loadGymsList()
        return getSortedGymsUseCase()
    }
}