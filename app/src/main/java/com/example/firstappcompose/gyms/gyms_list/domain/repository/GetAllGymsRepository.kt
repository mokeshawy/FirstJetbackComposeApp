package com.example.firstappcompose.gyms.gyms_list.domain.repository

import com.example.firstappcompose.gyms.gyms_list.domain.domain_model.GymsData

interface GetAllGymsRepository {

    suspend fun getAllGymsList() : List<GymsData>
}