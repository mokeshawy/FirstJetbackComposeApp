package com.example.firstappcompose.gyms.gyms_list.data.data_model.local_model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class LocalGymFavoriteState(
    @ColumnInfo("gym_id")
    val id: Int,
    @ColumnInfo("isFavorite")
    val isFavorite: Boolean = false
)