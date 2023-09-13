package com.example.firstappcompose.gyms.gyms_list.data.repository

import com.example.firstappcompose.application.GymsApplication
import com.example.firstappcompose.core.room.room.GymDatabase
import com.example.firstappcompose.gyms.gyms_list.data.data_model.local_model.LocalGymFavoriteState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetToggleFavoriteGymRepositoryImpl {


    private var gymDao = GymDatabase.getDaoInstance(GymsApplication.getApplicationContext())

    suspend fun toggleFavoriteGym(gymId: Int, state: Boolean) =
        withContext(Dispatchers.IO) {
            return@withContext gymDao.update(LocalGymFavoriteState(gymId, state))
        }

}