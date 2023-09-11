package com.example.firstappcompose.gym_activity.main_screen.data.repository

import com.example.firstappcompose.application.GymsApplication
import com.example.firstappcompose.core.gyms_api_servecies.GymsApiServices
import com.example.firstappcompose.core.room.room.GymDatabase
import com.example.firstappcompose.gym_activity.main_screen.domain.model.GymFavoriteState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GymsRepositoryImpl {

    private var gymsApiServices: GymsApiServices = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://jetbackcompose-default-rtdb.firebaseio.com/")
        .build().create(GymsApiServices::class.java)

    private var gymDao = GymDatabase.getDaoInstance(GymsApplication.getApplicationContext())

    suspend fun getAllGyms() = withContext(Dispatchers.IO) {
        try {
            updateLocalDatabase()
        } catch (e: Exception) {
            if (gymDao.getAll().isEmpty())
                throw Exception("Something want wrong, please try again later")
        }
        gymDao.getAll()
    }

    private suspend fun updateLocalDatabase() {
        val gyms = gymsApiServices.getGyms()
        val favoriteGymsList = gymDao.getFavoriteGyms()
        gymDao.addAll(gyms)
        gymDao.updateAll(
            favoriteGymsList.map {
                GymFavoriteState(id = it.id, true)
            }
        )
    }


    suspend fun toggleFavoriteGym(gymId: Int, currentFavoriteState: Boolean) =
        withContext(Dispatchers.IO) {
            gymDao.update(GymFavoriteState(gymId, currentFavoriteState))
            return@withContext gymDao.getAll()
        }
}