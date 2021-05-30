package com.example.mooncascadetest.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PlaceDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(placeEntities: List<PlaceEntity>)

    @Query("DELETE FROM PlaceEntity")
    fun deleteAll()
}