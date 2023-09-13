package com.example.firstappcompose.gyms.gyms_list.data.repository

import com.example.firstappcompose.application.GymsApplication
import com.example.firstappcompose.core.gyms_api_servecies.GymsApiServices
import com.example.firstappcompose.core.room.room.GymDatabase
import com.example.firstappcompose.gyms.gyms_list.data.data_model.local_model.LocalGym
import com.example.firstappcompose.gyms.gyms_list.data.data_model.local_model.LocalGymFavoriteState
import com.example.firstappcompose.gyms.gyms_list.domain.domain_model.GymsData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GetAllGymsRepositoryImpl {

    private var gymsApiServices: GymsApiServices = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://jetbackcompose-default-rtdb.firebaseio.com/")
        .build().create(GymsApiServices::class.java)

    private var gymDao = GymDatabase.getDaoInstance(GymsApplication.getApplicationContext())

    suspend fun getAllGymsList() = withContext(Dispatchers.IO) {
        gymDao.getAll().map { GymsData(it.id, it.location, it.name, it.gymStatus, it.isFavorite) }
    }

    suspend fun loadGymsList() = withContext(Dispatchers.IO) {
        try {
            updateLocalDatabase()
        } catch (e: Exception) {
            if (gymDao.getAll().isEmpty())
                throw Exception("Something want wrong, please try again later")
        }
    }

    private suspend fun updateLocalDatabase() {
        val gyms = gymsApiServices.getGyms()
        val favoriteGymsList = gymDao.getFavoriteGyms()
        gymDao.addAll(gyms.map { LocalGym(it.id, it.location, it.name, it.gymStatus) })
        gymDao.updateAll(
            favoriteGymsList.map {
                LocalGymFavoriteState(id = it.id, true)
            }
        )
    }
}