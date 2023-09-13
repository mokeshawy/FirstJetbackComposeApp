package com.example.firstappcompose.gyms.gyms_list.data.data_model.local_model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "gyms")
data class LocalGym(
    @PrimaryKey
    @ColumnInfo("gym_id")
    val id: Int,
    @ColumnInfo("gym_place")
    val location: String,
    @ColumnInfo("gym_name")
    val name: String,
    @ColumnInfo("gym_status")
    val gymStatus: Boolean,
    @ColumnInfo("isFavorite")
    val isFavorite: Boolean = false
)