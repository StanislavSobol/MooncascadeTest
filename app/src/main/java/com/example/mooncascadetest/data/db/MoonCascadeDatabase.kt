package com.example.mooncascadetest.data.db

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.*
import java.util.*

@Database(entities = [ForecastEntity::class, PlaceEntity::class, WindEntity::class], version = 11)
@TypeConverters(Converters::class)
abstract class MoonCascadeDatabase : RoomDatabase() {

    abstract fun getForecastDao(): ForecastDao

    abstract fun getPlaceDao(): PlaceDao

    abstract fun getWindDao(): WindDao

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

internal class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}