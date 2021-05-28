package com.example.mooncascadetest.data.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ForecastDao {

    @Transaction
    @Query("SELECT * FROM ForecastEntity")
    fun selectAll(): LiveData<List<ForecastWithPlacesAdWinds>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(forecast: ForecastEntity)

    @Query("DELETE FROM ForecastEntity")
    fun deleteAll()

}
