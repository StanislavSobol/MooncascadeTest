package com.example.mooncascadetest.presentation.mainscreen

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.mooncascadetest.data.db.ForecastWithPlacesAdWinds
import com.example.mooncascadetest.domain.mainscreen.MainScreenInteractor
import com.example.mooncascadetest.presentation.BaseViewModel
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(private val mainScreenInteractor: MainScreenInteractor) :
    BaseViewModel() {

    val vehiclesLiveData: LiveData<List<ForecastWithPlacesAdWinds>> = mainScreenInteractor.getForecastLiveData()
//        Transformations.map(mainScreenInteractor.getForecastLiveData()) { VehicleItem.listFromList(it, resourceManager) }

    init {
        launchWithProgressInDispatchersIO(hideLoadingStatusWhenDone = true) {
            Log.d("SSS", "MainScreenViewModel init")
            mainScreenInteractor.requestForecast()
        }
    }

}