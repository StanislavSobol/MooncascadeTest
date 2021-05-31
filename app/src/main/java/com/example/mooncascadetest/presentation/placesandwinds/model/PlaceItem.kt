package com.example.mooncascadetest.presentation.placesandwinds.model

import com.example.mooncascadetest.data.db.PlaceEntity
import com.example.mooncascadetest.tools.EMPTY_STRING
import com.example.mooncascadetest.tools.createTempRange
import com.example.mooncascadetest.tools.resourcemanager.ResourceManager

data class PlaceItem(
    override val id: Long,
    override val name: String,
    override val dayRange: String,
    override val nightRange: String
) :
    BasePlaceAndWindItem(
        providedType = PlaceAndWindsItemDelegateType.PLACE,
        id = id,
        name = name,
        dayRange = dayRange,
        nightRange = nightRange
    ) {

    companion object {

        fun from(placeEntity: PlaceEntity, resourceManager: ResourceManager) =
            PlaceItem(
                id = placeEntity.placeId,
                name = placeEntity.name ?: EMPTY_STRING,
                dayRange = createTempRange(
                    min = placeEntity.dayTempMin,
                    max = placeEntity.dayTempMax,
                    resourceManager = resourceManager
                ),
                nightRange = createTempRange(
                    min = placeEntity.nightTempMin,
                    max = placeEntity.nightTempMax,
                    resourceManager = resourceManager
                )
            )
    }
}