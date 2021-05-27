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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlace(place: PlaceEntity)

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertPlaces(places: List<PlaceEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWind(places: WindEntity)

    @Query("DELETE FROM ForecastEntity")
    fun deleteAll()

    @Query("DELETE FROM PlaceEntity")
    fun deleteAllPlaces()

    @Query("DELETE FROM WindEntity")
    fun deleteAllWinds()
}
