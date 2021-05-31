package com.example.mooncascadetest.presentation.placesandwinds

import com.example.mooncascadetest.data.db.PlaceEntity
import com.example.mooncascadetest.data.db.WindEntity
import com.example.mooncascadetest.tools.EMPTY_STRING
import com.example.mooncascadetest.tools.createTempRange
import com.example.mooncascadetest.tools.createWindRange
import com.example.mooncascadetest.tools.resourcemanager.ResourceManager

// TODO to diff files
interface PlaceAndWindsItemDelegate {

    val type: PlaceAndWindsItemDelegateType

    // TODO to root
    enum class PlaceAndWindsItemDelegateType(val typeInt: Int) {
        TITLE(0), PLACE(1), WIND(2)
    }
}

abstract class BasePlaceAndWindItem(
    private val providedType: PlaceAndWindsItemDelegate.PlaceAndWindsItemDelegateType,
    open val id: Long,
    open val name: String,
    open val dayRange: String,
    open val nightRange: String
) : PlaceAndWindsItemDelegate {

    override val type: PlaceAndWindsItemDelegate.PlaceAndWindsItemDelegateType
        get() = providedType
}

data class PlaceItem(
    override val id: Long,
    override val name: String,
    override val dayRange: String,
    override val nightRange: String
) :
    BasePlaceAndWindItem(
        providedType = PlaceAndWindsItemDelegate.PlaceAndWindsItemDelegateType.PLACE,
        id = id,
        name = name,
        dayRange = dayRange,
        nightRange = nightRange
    ) {

    companion object {

        fun from(placeEntity: PlaceEntity, resourceManager: ResourceManager) = PlaceItem(
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

data class WindItem(
    override val id: Long,
    override val name: String,
    override val dayRange: String,
    override val nightRange: String
) :
    BasePlaceAndWindItem(
        providedType = PlaceAndWindsItemDelegate.PlaceAndWindsItemDelegateType.WIND,
        id = id,
        name = name,
        dayRange = dayRange,
        nightRange = nightRange
    ) {

    companion object {

        fun from(windEntity: WindEntity, resourceManager: ResourceManager) = WindItem(
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

data class TitlePlaceAndWindsLisItem(val title: String) : PlaceAndWindsItemDelegate {

    override val type: PlaceAndWindsItemDelegate.PlaceAndWindsItemDelegateType
        get() = PlaceAndWindsItemDelegate.PlaceAndWindsItemDelegateType.TITLE
}