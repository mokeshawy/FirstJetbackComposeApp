package com.example.firstappcompose.gym_activity.main_screen.data.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "gyms")
data class GymsResponseDto(
    @PrimaryKey
    @ColumnInfo("gym_id")
    @SerializedName("id")
    val id: Int,
    @ColumnInfo("gym_place")
    @SerializedName("gym_location")
    val location: String,
    @ColumnInfo("gym_name")
    @SerializedName("gym_name")
    val name: String,
    @ColumnInfo("gym_status")
    @SerializedName("is_open")
    val gymStatus: Boolean,
    @ColumnInfo("isFavorite")
    val isFavorite: Boolean = false
)