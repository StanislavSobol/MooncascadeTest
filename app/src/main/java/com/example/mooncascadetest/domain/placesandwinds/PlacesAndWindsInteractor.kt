package com.example.mooncascadetest.domain.placesandwinds

import com.example.mooncascadetest.data.db.PlaceEntity
import com.example.mooncascadetest.data.db.WindEntity
import java.util.*

interface PlacesAndWindsInteractor {

    suspend fun getPlacesForDate(date: Date): List<PlaceEntity>

    suspend fun getWindsForDate(date: Date): List<WindEntity>
}