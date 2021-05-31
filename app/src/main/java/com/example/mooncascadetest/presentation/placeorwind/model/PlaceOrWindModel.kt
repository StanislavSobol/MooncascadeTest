package com.example.mooncascadetest.presentation.placeorwind.model

import com.example.mooncascadetest.data.db.PlaceEntity
import com.example.mooncascadetest.data.db.WindEntity
import com.example.mooncascadetest.tools.EMPTY_STRING
import com.example.mooncascadetest.tools.createTempRange
import com.example.mooncascadetest.tools.createWindRange
import com.example.mooncascadetest.tools.resourcemanager.ResourceManager

data class PlaceOrWindModel(
    val isPlace: Boolean,
    val name: String,
    val dayDescr: String,
    val dayRange: String,
    val nightDescr: String,
    val nightRange: String
) {
    companion object {

        fun fromPlaceEntity(placeEntity: PlaceEntity, resourceManager: ResourceManager) =
            PlaceOrWindModel(
                isPlace = true,
                name = placeEntity.name ?: EMPTY_STRING,
                dayDescr = placeEntity.dayPhenomenon ?: EMPTY_STRING,
                dayRange = createTempRange(
                    min = placeEntity.dayTempMin,
                    max = placeEntity.dayTempMax,
                    resourceManager = resourceManager
                ),
                nightDescr = placeEntity.nightPhenomenon ?: EMPTY_STRING,
                nightRange = createTempRange(
                    min = placeEntity.nightTempMin,
                    max = placeEntity.nightTempMax,
                    resourceManager = resourceManager
                )
            )

        fun fromWindEntity(wiindEntity: WindEntity, resourceManager: ResourceManager) =
            PlaceOrWindModel(
                isPlace = false,
                name = wiindEntity.name ?: EMPTY_STRING,
                dayDescr = wiindEntity.dayDirection ?: EMPTY_STRING,
                dayRange = createWindRange(
                    min = wiindEntity.daySpeedMin,
                    max = wiindEntity.daySpeedMax,
                    resourceManager = resourceManager
                ),
                nightDescr = wiindEntity.nightDirection ?: EMPTY_STRING,
                nightRange = createWindRange(
                    min = wiindEntity.nightSpeedMin,
                    max = wiindEntity.nightSpeedMax,
                    resourceManager = resourceManager
                )
            )
    }
}