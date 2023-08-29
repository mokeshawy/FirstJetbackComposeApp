package com.example.firstappcompose.gym_activity.main_screen.domain.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.firstappcompose.gym_activity.main_screen.data.response.GymsResponseDto

@Dao
interface GymsDAO {

    @Query("SELECT * FROM gyms")
    suspend fun getAll(): List<GymsResponseDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(gyms: List<GymsResponseDto>)

}