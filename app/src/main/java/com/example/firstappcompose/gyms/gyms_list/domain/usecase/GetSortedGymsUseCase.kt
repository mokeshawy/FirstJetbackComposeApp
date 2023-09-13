package com.example.firstappcompose.gyms.gyms_list.domain.usecase

import com.example.firstappcompose.gyms.gyms_list.data.repository.GetAllGymsRepositoryImpl

class GetSortedGymsUseCase {

    private val getAllGymRepositoryImpl = GetAllGymsRepositoryImpl()
    suspend operator fun invoke() = getAllGymRepositoryImpl.getAllGymsList().sortedBy { it.name }
}