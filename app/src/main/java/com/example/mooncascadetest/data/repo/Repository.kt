package com.example.mooncascadetest.data.repo

import androidx.lifecycle.LiveData
import com.example.mooncascadetest.data.db.ForecastWithPlacesAndWindsEntity
import com.example.mooncascadetest.data.db.PlaceEntity
import com.example.mooncascadetest.data.db.WindEntity
import java.util.*

interface Repository {

    fun getForecastLiveData(): LiveData<List<ForecastWithPlacesAndWindsEntity>>

    suspend fun requestForecastAndPlaceToDb()

    suspend fun getPlacesForDate(date: Date): List<PlaceEntity>

    suspend fun getWindsForDate(date: Date): List<WindEntity>

    suspend fun getPlaceEntityById(placeId: Long): PlaceEntity

    suspend fun getWindEntityById(windId: Long): WindEntity
}