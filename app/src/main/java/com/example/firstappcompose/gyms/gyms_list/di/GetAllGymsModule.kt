package com.example.firstappcompose.gyms.gyms_list.di

import com.example.firstappcompose.core.gyms_api_servecies.GymsApiServices
import com.example.firstappcompose.core.room.room.GymsDAO
import com.example.firstappcompose.gyms.gyms_list.data.repository.GetAllGymsRepositoryImpl
import com.example.firstappcompose.gyms.gyms_list.data.repository.GetToggleFavoriteGymRepositoryImpl
import com.example.firstappcompose.gyms.gyms_list.domain.repository.GetAllGymsRepository
import com.example.firstappcompose.gyms.gyms_list.domain.repository.GetToggleFavoriteGymRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object GetAllGymsModule {


    @Provides
    fun provideGetAllGymsRepository(
        gymsApiServices: GymsApiServices,
        gymsDAO: GymsDAO
    ): GetAllGymsRepository =
        GetAllGymsRepositoryImpl(gymsApiServices, gymsDAO)

    @Provides
    fun provideGetToggleFavoriteGymRepository(gymsDAO: GymsDAO): GetToggleFavoriteGymRepository =
        GetToggleFavoriteGymRepositoryImpl(gymsDAO)
}