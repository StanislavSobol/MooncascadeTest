package com.example.mooncascadetest.presentation.placesandwinds.model

data class TitlePlaceAndWindsLisItem(val title: String) :
    PlaceAndWindsItemDelegate {

    override val type: PlaceAndWindsItemDelegateType
        get() = PlaceAndWindsItemDelegateType.TITLE
}