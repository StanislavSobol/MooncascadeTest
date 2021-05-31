package com.example.mooncascadetest.domain.placeorwind

import com.example.mooncascadetest.data.db.PlaceEntity
import com.example.mooncascadetest.data.db.WindEntity

interface PlaceOrWindInteractor {

    fun getPlaceEntityById(placeId: Long): PlaceEntity

    fun getWindEntityById(windId: Long): WindEntity
}