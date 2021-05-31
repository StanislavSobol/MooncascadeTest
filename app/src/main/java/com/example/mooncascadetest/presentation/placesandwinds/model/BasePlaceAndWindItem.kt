package com.example.mooncascadetest.presentation.placesandwinds.model

abstract class BasePlaceAndWindItem(
    private val providedType: PlaceAndWindsItemDelegateType,
    open val id: Long,
    open val name: String,
    open val dayRange: String,
    open val nightRange: String
) : PlaceAndWindsItemDelegate {

    override val type: PlaceAndWindsItemDelegateType
        get() = providedType
}