package com.example.mooncascadetest.presentation.placesandwinds

import com.example.mooncascadetest.data.db.PlaceEntity
import com.example.mooncascadetest.data.db.WindEntity
import com.example.mooncascadetest.tools.EMPTY_STRING
import com.example.mooncascadetest.tools.createTempRange
import com.example.mooncascadetest.tools.createWindRange
import com.example.mooncascadetest.tools.resourcemanager.ResourceManager

interface PlaceAndWindsItemDelegate {

    val type: PlaceAndWindsItemDelegateType

    enum class PlaceAndWindsItemDelegateType(val type: Int) {
        ITEM(0), TITLE(2)
    }
}

data class PlaceAndWindsItem(val name: String, val range: String) : PlaceAndWindsItemDelegate {

    override val type: PlaceAndWindsItemDelegate.PlaceAndWindsItemDelegateType
        get() = PlaceAndWindsItemDelegate.PlaceAndWindsItemDelegateType.ITEM

    companion object {

        fun from(placeEntity: PlaceEntity, resourceManager: ResourceManager) = PlaceAndWindsItem(
            name = placeEntity.name ?: EMPTY_STRING,
            range = createTempRange(
                min = placeEntity.tempmin,
                max = placeEntity.tempmax,
                resourceManager = resourceManager
            )
        )

        fun from(windEntity: WindEntity, resourceManager: ResourceManager) = PlaceAndWindsItem(
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