package com.example.mooncascadetest.presentation.placeorwind

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mooncascadetest.domain.placeorwind.PlaceOrWindInteractor
import com.example.mooncascadetest.presentation.BaseViewModel
import com.example.mooncascadetest.presentation.placesandwinds.PlaceAndWindsItemDelegate
import com.example.mooncascadetest.tools.resourcemanager.ResourceManager
import javax.inject.Inject

class PlaceOrWindViewModel @Inject constructor(
    private val placeOrWindInteractor: PlaceOrWindInteractor,
    // TODO isPLace
    private val type: Int,
    private val id: Long,
    private val resourceManager: ResourceManager
) : BaseViewModel() {

    private val _placeOrWindLiveData: MutableLiveData<PlaceOrWindModel> = MutableLiveData()
    val placeOrWindLiveData: LiveData<PlaceOrWindModel>
        get() = _placeOrWindLiveData

    init {
        launchWithProgressInDispatchersIO(hideLoadingStatusWhenDone = true) {
            when (type) {
                PlaceAndWindsItemDelegate.PlaceAndWindsItemDelegateType.PLACE.typeInt -> {
                    val placeEntity = placeOrWindInteractor.getPlaceEntityById(id)
                    _placeOrWindLiveData.postValue(PlaceOrWindModel.fromPlaceEntity(placeEntity, resourceManager))
                }
                PlaceAndWindsItemDelegate.PlaceAndWindsItemDelegateType.WIND.typeInt -> {
                    val windEntity = placeOrWindInteractor.getWindEntityById(id)
                    _placeOrWindLiveData.postValue(PlaceOrWindModel.fromWindEntity(windEntity, resourceManager))
                }
            }
        }
    }
}