package com.example.mooncascadetest.presentation.mainscreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.mooncascadetest.data.db.ForecastWithPlacesAdWinds
import com.example.mooncascadetest.domain.mainscreen.MainScreenInteractor
import com.example.mooncascadetest.presentation.BaseViewModel
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(private val mainScreenInteractor: MainScreenInteractor) :
    BaseViewModel() {

    val vehiclesLiveData: LiveData<List<ForecastWithPlacesAdWinds>> = mainScreenInteractor.getForecastLiveData()
//        Transformations.map(mainScreenInteractor.getForecastLiveData()) { VehicleItem.listFromList(it, resourceManager) }

    val forecastLiveData: LiveData<List<MainScreenListItemDelegate>> =
        Transformations.map(mainScreenInteractor.getForecastLiveData()) {
            val result = mutableListOf<MainScreenListItemDelegate>()

            it.forEachIndexed { index, forecastWithPlacesAdWinds ->
                if (index == 0) {
                    result.add(
                        DayForecastMainScreenListItem.from(
                            type = MainScreenListItemDelegateType.CURRENT,
                            forecastWithPlacesAdWinds = forecastWithPlacesAdWinds
                        )
                    )
                    if (it.size > 1) {
                        // TODO Res
                        result.add(TitleMainScreenLisItem("Titol"))
                    }
                } else {
                    result.add(
                        DayForecastMainScreenListItem.from(
                            type = MainScreenListItemDelegateType.FUTURE,
                            forecastWithPlacesAdWinds = forecastWithPlacesAdWinds
                        )
                    )
                }
            }
            result
        }

    init {
        launchWithProgressInDispatchersIO(hideLoadingStatusWhenDone = true) {
            Log.d("SSS", "MainScreenViewModel init")
            mainScreenInteractor.requestForecast()
        }
    }

}