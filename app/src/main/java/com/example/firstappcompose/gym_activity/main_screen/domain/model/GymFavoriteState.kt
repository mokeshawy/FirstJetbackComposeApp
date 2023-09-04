package com.example.firstappcompose.gym_activity.main_screen.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class GymFavoriteState(
    @ColumnInfo("gym_id")
    val id: Int,
    @ColumnInfo("isFavorite")
    val isFavorite: Boolean = false
)