package com.example.firstappcompose.core.room.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.firstappcompose.gym_activity.main_screen.data.response.GymsResponseDto
import com.example.firstappcompose.gym_activity.main_screen.domain.model.GymFavoriteState

@Dao
interface GymsDAO {

    @Query("SELECT * FROM gyms")
    suspend fun getAll(): List<GymsResponseDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(gyms: List<GymsResponseDto>)

    @Update(entity = GymsResponseDto::class)
    suspend fun update(gymFavoriteState: GymFavoriteState)

    @Query("SELECT * FROM gyms WHERE isFavorite = 1 ")
    suspend fun getFavoriteGyms(): List<GymsResponseDto>

    @Update(entity = GymsResponseDto::class)
    suspend fun updateAll(gymFavoriteState: List<GymFavoriteState>)

    @Query("SELECT * FROM gyms WHERE gym_id= :id")
    suspend fun getGymDetails(id: Int): GymsResponseDto

}