package com.example.mooncascadetest.presentation.placeorwind

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mooncascadetest.domain.placeorwind.PlaceOrWindInteractor
import com.example.mooncascadetest.presentation.BaseViewModel
import com.example.mooncascadetest.presentation.placeorwind.model.PlaceOrWindModel
import com.example.mooncascadetest.tools.resourcemanager.ResourceManager
import javax.inject.Inject

class PlaceOrWindViewModel @Inject constructor(
    private val placeOrWindInteractor: PlaceOrWindInteractor,
    private val isPlace: Boolean,
    private val id: Long,
    private val resourceManager: ResourceManager
) : BaseViewModel() {

    private val _placeOrWindLiveData: MutableLiveData<PlaceOrWindModel> = MutableLiveData()
    val placeOrWindLiveData: LiveData<PlaceOrWindModel>
        get() = _placeOrWindLiveData

    init {
        launchWithProgressInDispatchersIO() {
            if (isPlace) {
                val placeEntity = placeOrWindInteractor.getPlaceEntityById(id)
                _placeOrWindLiveData.postValue(PlaceOrWindModel.fromPlaceEntity(placeEntity, resourceManager))
            } else {
                val windEntity = placeOrWindInteractor.getWindEntityById(id)
                _placeOrWindLiveData.postValue(PlaceOrWindModel.fromWindEntity(windEntity, resourceManager))
            }
        }
    }
}