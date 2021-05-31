package com.example.mooncascadetest.presentation.placesandwinds.model

import com.example.mooncascadetest.data.db.WindEntity
import com.example.mooncascadetest.tools.EMPTY_STRING
import com.example.mooncascadetest.tools.createWindRange
import com.example.mooncascadetest.tools.resourcemanager.ResourceManager

data class WindItem(
    override val id: Long,
    override val name: String,
    override val dayRange: String,
    override val nightRange: String
) :
    BasePlaceAndWindItem(
        providedType = PlaceAndWindsItemDelegateType.WIND,
        id = id,
        name = name,
        dayRange = dayRange,
        nightRange = nightRange
    ) {

    companion object {

        fun from(windEntity: WindEntity, resourceManager: ResourceManager) =
            WindItem(
                id = windEntity.windId,
                name = windEntity.name ?: EMPTY_STRING,
                dayRange = createWindRange(
                    min = windEntity.daySpeedMin,
                    max = windEntity.daySpeedMax,
                    resourceManager = resourceManager
                ),
                nightRange = createWindRange(
                    min = windEntity.nightSpeedMin,
                    max = windEntity.nightSpeedMax,
                    resourceManager = resourceManager
                )
            )
    }
}