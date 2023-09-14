package com.example.firstappcompose.gyms.gyms_list.domain.usecase

import com.example.firstappcompose.gyms.gyms_list.data.repository.GetAllGymsRepositoryImpl
import javax.inject.Inject

class GetSortedGymsUseCase @Inject constructor(private val getAllGymRepositoryImpl : GetAllGymsRepositoryImpl) {

    suspend operator fun invoke() = getAllGymRepositoryImpl.getAllGymsList().sortedBy { it.name }
}