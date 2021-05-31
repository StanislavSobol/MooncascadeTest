package com.example.mooncascadetest.data.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ForecastDao {

    @Transaction
    @Query("SELECT * FROM ForecastEntity")
    fun selectAll(): LiveData<List<ForecastWithPlacesAndWindsEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(forecastEntities: List<ForecastEntity>)

    @Query("DELETE FROM ForecastEntity")
    fun deleteAll()
}
