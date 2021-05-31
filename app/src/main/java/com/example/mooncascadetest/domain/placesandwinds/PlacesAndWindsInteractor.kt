package com.example.mooncascadetest.domain.placesandwinds

import androidx.lifecycle.LiveData
import com.example.mooncascadetest.data.db.PlaceEntity
import com.example.mooncascadetest.data.db.WindEntity
import java.util.*

interface PlacesAndWindsInteractor {

    @Deprecated("use getPlacesForDate")
    fun getPlacesForDateLiveData(date: Date): LiveData<List<PlaceEntity>>

    @Deprecated("use getWindsForDate")
    fun getWindsForDateLiveData(date: Date): LiveData<List<WindEntity>>

    suspend fun getPlacesForDate(date: Date): List<PlaceEntity>

    suspend fun getWindsForDate(date: Date): List<WindEntity>
}