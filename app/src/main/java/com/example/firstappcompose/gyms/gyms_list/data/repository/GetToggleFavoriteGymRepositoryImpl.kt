package com.example.firstappcompose.gyms.gyms_list.data.repository

import com.example.firstappcompose.core.room.room.GymsDAO
import com.example.firstappcompose.gyms.gyms_list.data.data_model.local_model.LocalGymFavoriteState
import com.example.firstappcompose.gyms.gyms_list.domain.repository.GetToggleFavoriteGymRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetToggleFavoriteGymRepositoryImpl @Inject constructor(private val gymsDAO: GymsDAO) :
    GetToggleFavoriteGymRepository {

    override suspend fun toggleFavoriteGym(gymId: Int, state: Boolean) =
        withContext(Dispatchers.IO) {
            return@withContext gymsDAO.update(LocalGymFavoriteState(gymId, state))
        }
}