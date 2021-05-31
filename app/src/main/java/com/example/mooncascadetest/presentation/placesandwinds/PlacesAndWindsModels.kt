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

    enum class PlaceAndWindsItemDelegateType(val type: Int) {
        TITLE(0), PLACE(1), WIND(2)
    }
}

abstract class BasePlaceAndWindItem(
    private val providedType: PlaceAndWindsItemDelegate.PlaceAndWindsItemDelegateType,
    open val id: Int,
    open val name: String,
    open val range: String
) : PlaceAndWindsItemDelegate {

    override val type: PlaceAndWindsItemDelegate.PlaceAndWindsItemDelegateType
        get() = providedType
}

data class PlaceItem(override val id: Int, override val name: String, override val range: String) :
    BasePlaceAndWindItem(
        providedType = PlaceAndWindsItemDelegate.PlaceAndWindsItemDelegateType.PLACE,
        id = id,
        name = name,
        range = range
    ) {

    companion object {

        fun from(placeEntity: PlaceEntity, resourceManager: ResourceManager) = PlaceItem(
            id = placeEntity.placeId,
            name = placeEntity.name ?: EMPTY_STRING,
            range = createTempRange(
                min = placeEntity.tempmin,
                max = placeEntity.tempmax,
                resourceManager = resourceManager
            )
        )
    }
}

data class WindItem(override val id: Int, override val name: String, override val range: String) : BasePlaceAndWindItem(
    providedType = PlaceAndWindsItemDelegate.PlaceAndWindsItemDelegateType.WIND,
    id = id,
    name = name,
    range = range
) {

    companion object {

        fun from(windEntity: WindEntity, resourceManager: ResourceManager) = WindItem(
            id = windEntity.windId,
            name = windEntity.name ?: EMPTY_STRING,
            range = createWindRange(
                min = windEntity.speedmin,
                max = windEntity.speedmax,
                resourceManager = resourceManager
            )
        )
    }
}

data class TitlePlaceAndWindsLisItem(val title: String) : PlaceAndWindsItemDelegate {

    override val type: PlaceAndWindsItemDelegate.PlaceAndWindsItemDelegateType
        get() = PlaceAndWindsItemDelegate.PlaceAndWindsItemDelegateType.TITLE
}