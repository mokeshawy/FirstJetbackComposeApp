package com.example.firstappcompose.core.network_di

import android.content.Context
import com.example.firstappcompose.R
import com.example.firstappcompose.core.gyms_api_servecies.GymsApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ActivityRetainedComponent::class)
object NetworkModule {

    @ActivityRetainedScoped
    @Provides
    fun provideGymsApiServices(retrofit: Retrofit): GymsApiServices =
        retrofit.create(GymsApiServices::class.java)

    @ActivityRetainedScoped
    @Provides
    fun provideRetrofit(@ApplicationContext context: Context) = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(context.getString(R.string.base_url))
        .build()
}