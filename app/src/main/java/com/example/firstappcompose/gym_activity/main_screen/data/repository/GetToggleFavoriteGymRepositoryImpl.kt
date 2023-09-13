package com.example.firstappcompose.gym_activity.main_screen.data.repository

import com.example.firstappcompose.application.GymsApplication
import com.example.firstappcompose.core.room.room.GymDatabase
import com.example.firstappcompose.gym_activity.main_screen.domain.model.GymFavoriteState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetToggleFavoriteGymRepositoryImpl {


    private var gymDao = GymDatabase.getDaoInstance(GymsApplication.getApplicationContext())

    suspend fun toggleFavoriteGym(gymId: Int, state: Boolean) =
        withContext(Dispatchers.IO) {
            return@withContext gymDao.update(GymFavoriteState(gymId, state))
        }

}