package com.example.firstappcompose.core.room.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.firstappcompose.gyms.gyms_list.data.data_model.local_model.LocalGym
import com.example.firstappcompose.gyms.gyms_list.data.data_model.local_model.LocalGymFavoriteState

@Dao
interface GymsDAO {

    @Query("SELECT * FROM gyms")
    suspend fun getAll(): List<LocalGym>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(gyms: List<LocalGym>)

    @Update(entity = LocalGym::class)
    suspend fun update(localGymFavoriteState: LocalGymFavoriteState)

    @Query("SELECT * FROM gyms WHERE isFavorite = 1 ")
    suspend fun getFavoriteGyms(): List<LocalGym>

    @Update(entity = LocalGym::class)
    suspend fun updateAll(localGymFavoriteState: List<LocalGymFavoriteState>)

    @Query("SELECT * FROM gyms WHERE gym_id= :id")
    suspend fun getGymDetails(id: Int): LocalGym

}