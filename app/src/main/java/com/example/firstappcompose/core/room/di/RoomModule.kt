package com.example.firstappcompose.core.room.di

import android.content.Context
import androidx.room.Room
import com.example.firstappcompose.core.room.room.GymDatabase
import com.example.firstappcompose.core.room.room.GymsDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    fun provideGymDao(gymDatabase: GymDatabase): GymsDAO = gymDatabase.gymDao()


    @Provides
    @Singleton
    fun provideGymDatabase(@ApplicationContext context: Context): GymDatabase =
        Room.databaseBuilder(context, GymDatabase::class.java, "gyms_database")
            .fallbackToDestructiveMigration().build()
}