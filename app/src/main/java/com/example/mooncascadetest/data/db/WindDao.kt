package com.example.mooncascadetest.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WindDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(places: WindEntity)

    @Query("DELETE FROM WindEntity")
    fun deleteAll()
}