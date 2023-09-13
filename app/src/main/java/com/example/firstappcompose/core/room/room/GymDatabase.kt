package com.example.firstappcompose.core.room.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.firstappcompose.gyms.gyms_list.data.data_model.local_model.LocalGym

@Database(
    entities = [LocalGym::class],
    version = 3,
    exportSchema = false
)
abstract class GymDatabase : RoomDatabase() {

    abstract val dao: GymsDAO

    companion object {

        @Volatile // will be visible in other threads
        private var daoInstance: GymsDAO? = null
        private fun buildDatabase(context: Context): GymDatabase = Room.databaseBuilder(
            context.applicationContext,
            GymDatabase::class.java,
            "gyms_database"
        ).fallbackToDestructiveMigration().build()

        fun getDaoInstance(context: Context): GymsDAO {
            synchronized(this) { // work on call one time only for this method
                if (daoInstance == null) {
                    daoInstance = buildDatabase(context).dao
                }
                return daoInstance as GymsDAO
            }
        }
    }
}