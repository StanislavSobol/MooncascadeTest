package com.example.mooncascadetest.data.repo

import androidx.lifecycle.LiveData
import com.example.mooncascadetest.data.db.ForecastWithPlacesAndWindsEntity
import com.example.mooncascadetest.data.db.PlaceEntity
import com.example.mooncascadetest.data.db.WindEntity
import java.util.*

interface Repository {

    fun getForecastLiveData(): LiveData<List<ForecastWithPlacesAndWindsEntity>>

    suspend fun requestForecastAndPlaceToDb()

    @Deprecated("getPlacesForDate")
    fun getPlacesForDateLiveData(date: Date): LiveData<List<PlaceEntity>>

    @Deprecated("getWindsForDate")
    fun getWindsForDateLiveData(date: Date): LiveData<List<WindEntity>>

    fun getPlacesForDate(date: Date): List<PlaceEntity>

    fun getWindsForDate(date: Date): List<WindEntity>
}