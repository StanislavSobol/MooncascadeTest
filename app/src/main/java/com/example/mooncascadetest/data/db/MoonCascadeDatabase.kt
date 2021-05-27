package com.example.mooncascadetest.data.db

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ForecastEntity::class], version = 1)
//@TypeConverters(Converters::class)
abstract class MoonCascadeDatabase : RoomDatabase() {

//    abstract fun getVehiclesDao(): VehiclesDao

    companion object {

        private var instance: MoonCascadeDatabase? = null

        @VisibleForTesting
        fun getTestInstance(appContext: Context): MoonCascadeDatabase {
            if (instance == null) {
                synchronized(MoonCascadeDatabase::class) {
                    instance = Room
                        .inMemoryDatabaseBuilder(appContext, MoonCascadeDatabase::class.java)
                        .build()
                }
            }
            return instance!!
        }
    }
}

// TODO Witch this
//internal class Converters {
//    @TypeConverter
//    fun fromTimestamp(value: Long?): Date? {
//        return value?.let { Date(it) }
//    }
//
//    @TypeConverter
//    fun dateToTimestamp(date: Date?): Long? {
//        return date?.time
//    }
//}