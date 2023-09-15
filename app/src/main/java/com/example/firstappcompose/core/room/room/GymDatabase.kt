package com.example.firstappcompose.core.room.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.firstappcompose.gyms.gyms_list.data.data_model.local_model.LocalGym

@Database(
    entities = [LocalGym::class],
    version = 3,
    exportSchema = false
)
abstract class GymDatabase : RoomDatabase() {
    abstract fun gymDao(): GymsDAO
}