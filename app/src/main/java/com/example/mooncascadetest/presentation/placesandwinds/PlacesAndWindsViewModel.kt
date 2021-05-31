package com.example.mooncascadetest.presentation.placesandwinds

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.mooncascadetest.R
import com.example.mooncascadetest.domain.placesandwinds.PlacesAndWindsInteractor
import com.example.mooncascadetest.presentation.BaseViewModel
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

//    val placesAndWindsLiveData: LiveData<List<PlaceAndWindsItemDelegate?>> = getCombinedLiveData()

    init {
        launchWithProgressInDispatchersIO(hideLoadingStatusWhenDone = true) {
            val placesAndWindsItems = mutableListOf<PlaceAndWindsItemDelegate>()

            val places = placesAndWindsInteractor.getPlacesForDate(date).map { placeEntity ->
                PlaceAndWindsItem.from(placeEntity, resourceManager)
            }
            places.let {
                if (it.isNotEmpty()) {
                    placesAndWindsItems.add(TitlePlaceAndWindsLisItem(resourceManager.getString(R.string.places)))
                    placesAndWindsItems.addAll(it)
                }
            }

            val winds = placesAndWindsInteractor.getWindsForDate(date).map { placeEntity ->
                PlaceAndWindsItem.from(placeEntity, resourceManager)
            }
            winds.let {
                if (it.isNotEmpty()) {
                    placesAndWindsItems.add(TitlePlaceAndWindsLisItem(resourceManager.getString(R.string.winds)))
                    placesAndWindsItems.addAll(it)
                }
            }

            _placesAndWindsLiveData.postValue(placesAndWindsItems)
        }
    }

    fun onPlacesAndWindsClicked(int: Int) {

    }

    private fun getCombinedLiveData(): LiveData<List<PlaceAndWindsItemDelegate?>> {
        val mediator: MediatorLiveData<List<PlaceAndWindsItemDelegate?>> = MediatorLiveData()
        mediator.value = null

        mediator.addSource(placesAndWindsInteractor.getPlacesForDateLiveData(date)) {
            val places = mutableListOf<PlaceAndWindsItemDelegate>()
            places.add(TitlePlaceAndWindsLisItem(resourceManager.getString(R.string.places)))
            places.addAll(it.map { placeEntity -> PlaceAndWindsItem.from(placeEntity, resourceManager) })
            mediator.value = places
        }

        mediator.addSource(placesAndWindsInteractor.getWindsForDateLiveData(date)) {
            val places = mutableListOf<PlaceAndWindsItemDelegate>()
            places.add(TitlePlaceAndWindsLisItem(resourceManager.getString(R.string.winds)))
            places.addAll(it.map { windEntity -> PlaceAndWindsItem.from(windEntity, resourceManager) })
            mediator.value = places
        }

        return mediator
    }
}