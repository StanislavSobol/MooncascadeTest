package com.example.mooncascadetest.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PlaceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(place: PlaceEntity)

    @Query("DELETE FROM PlaceEntity")
    fun deleteAll()
}