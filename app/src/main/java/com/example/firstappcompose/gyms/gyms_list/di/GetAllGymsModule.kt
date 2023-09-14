package com.example.firstappcompose.gyms.gyms_list.di

import com.example.firstappcompose.core.gyms_api_servecies.GymsApiServices
import com.example.firstappcompose.gyms.gyms_list.data.repository.GetAllGymsRepositoryImpl
import com.example.firstappcompose.gyms.gyms_list.domain.repository.GetAllGymsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object GetAllGymsModule {


    @Provides
    fun provideGetAllGymsRepository(gymsApiServices: GymsApiServices): GetAllGymsRepository =
        GetAllGymsRepositoryImpl(gymsApiServices)
}