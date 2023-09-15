package com.example.firstappcompose.gyms.gyms_list.data.repository

import com.example.firstappcompose.core.gyms_api_servecies.GymsApiServices
import com.example.firstappcompose.core.room.room.GymsDAO
import com.example.firstappcompose.gyms.gyms_list.data.data_model.local_model.LocalGym
import com.example.firstappcompose.gyms.gyms_list.data.data_model.local_model.LocalGymFavoriteState
import com.example.firstappcompose.gyms.gyms_list.domain.domain_model.GymsData
import com.example.firstappcompose.gyms.gyms_list.domain.repository.GetAllGymsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAllGymsRepositoryImpl @Inject constructor(
    private val gymsApiServices: GymsApiServices,
    private val gymsDAO: GymsDAO,
) : GetAllGymsRepository {


    override suspend fun getAllGymsList() = withContext(Dispatchers.IO) {
        gymsDAO.getAll().map { GymsData(it.id, it.location, it.name, it.gymStatus, it.isFavorite) }
    }

    suspend fun loadGymsList() = withContext(Dispatchers.IO) {
        try {
            updateLocalDatabase()
        } catch (e: Exception) {
            if (gymsDAO.getAll().isEmpty())
                throw Exception("Something want wrong, please try again later")
        }
    }

    private suspend fun updateLocalDatabase() {
        val gyms = gymsApiServices.getGyms()
        val favoriteGymsList = gymsDAO.getFavoriteGyms()
        gymsDAO.addAll(gyms.map { LocalGym(it.id, it.location, it.name, it.gymStatus) })
        gymsDAO.updateAll(
            favoriteGymsList.map {
                LocalGymFavoriteState(id = it.id, true)
            }
        )
    }
}