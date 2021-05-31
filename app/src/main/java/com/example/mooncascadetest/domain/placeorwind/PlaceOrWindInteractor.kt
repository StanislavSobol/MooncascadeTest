package com.example.mooncascadetest.domain.placeorwind

import com.example.mooncascadetest.data.db.PlaceEntity
import com.example.mooncascadetest.data.db.WindEntity

interface PlaceOrWindInteractor {

    suspend fun getPlaceEntityById(placeId: Long): PlaceEntity

    suspend fun getWindEntityById(windId: Long): WindEntity
}