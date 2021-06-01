package com.example.mooncascadetest.presentation.placesandwinds

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mooncascadetest.R
import com.example.mooncascadetest.domain.placesandwinds.PlacesAndWindsInteractor
import com.example.mooncascadetest.presentation.BaseViewModel
import com.example.mooncascadetest.presentation.placesandwinds.model.*
import com.example.mooncascadetest.tools.OneShotEvent
import com.example.mooncascadetest.tools.resourcemanager.ResourceManager
import java.util.*
import javax.inject.Inject

class PlacesAndWindsViewModel @Inject constructor(
    private val placesAndWindsInteractor: PlacesAndWindsInteractor,
    private val resourceManager: ResourceManager,
    private val date: Date
) : BaseViewModel() {

    private val _placesAndWindsLiveData = MutableLiveData<List<PlaceAndWindsItemDelegate>>()
    val placesAndWindsLiveData: LiveData<List<PlaceAndWindsItemDelegate>>
        get() = _placesAndWindsLiveData

    private val _toPlaceOrWindDetailEvent = MutableLiveData<OneShotEvent<BasePlaceAndWindItem>>()
    val toPlaceOrWindDetailEvent: LiveData<OneShotEvent<BasePlaceAndWindItem>>
        get() = _toPlaceOrWindDetailEvent

    init {
        launchWithProgressInDispatchersIO() {
            val placesAndWindsItems = mutableListOf<PlaceAndWindsItemDelegate>()

            val places = placesAndWindsInteractor.getPlacesForDate(date).map { placeEntity ->
                PlaceItem.from(placeEntity, resourceManager)
            }
            places.let {
                if (it.isNotEmpty()) {
                    placesAndWindsItems.add(
                        TitlePlaceAndWindsLisItem(
                            resourceManager.getString(R.string.places)
                        )
                    )
                    placesAndWindsItems.addAll(it)
                }
            }

            val winds = placesAndWindsInteractor.getWindsForDate(date).map { placeEntity ->
                WindItem.from(placeEntity, resourceManager)
            }
            winds.let {
                if (it.isNotEmpty()) {
                    placesAndWindsItems.add(
                        TitlePlaceAndWindsLisItem(
                            resourceManager.getString(R.string.winds)
                        )
                    )
                    placesAndWindsItems.addAll(it)
                }
            }

            _placesAndWindsLiveData.postValue(placesAndWindsItems)
        }
    }

    @MainThread
    fun onItemClicked(item: BasePlaceAndWindItem) {
        _toPlaceOrWindDetailEvent.value = OneShotEvent(item)
    }
}