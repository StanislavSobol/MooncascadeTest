package com.example.mooncascadetest.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.util.*

@Dao
interface PlaceDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(placeEntities: List<PlaceEntity>)

    @Query("DELETE FROM PlaceEntity")
    fun deleteAll()

    @Query("SELECT * FROM PlaceEntity WHERE date=:date ORDER BY placeId")
    fun selectForDate(date: Date): List<PlaceEntity>

    @Query("SELECT * FROM PlaceEntity WHERE placeId = :placeId")
    fun getByPlaceId(placeId: Long): PlaceEntity
}