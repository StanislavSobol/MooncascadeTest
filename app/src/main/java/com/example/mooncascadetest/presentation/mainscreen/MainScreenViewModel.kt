package com.example.mooncascadetest.presentation.mainscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.mooncascadetest.R
import com.example.mooncascadetest.domain.mainscreen.MainScreenInteractor
import com.example.mooncascadetest.presentation.BaseViewModel
import com.example.mooncascadetest.tools.resourcemanager.ResourceManager
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
    private val mainScreenInteractor: MainScreenInteractor,
    private val resourceManager: ResourceManager
) : BaseViewModel() {

    val forecastLiveData: LiveData<List<MainScreenListItemDelegate>> =
        Transformations.map(mainScreenInteractor.getForecastLiveData()) {
            val result = mutableListOf<MainScreenListItemDelegate>()
            it.forEachIndexed { index, forecastWithPlacesAndWinds ->
                if (index == 0) {
                    result.add(TitleMainScreenLisItem(resourceManager.getString(R.string.today)))
                    result.add(
                        DayForecastMainScreenListItem.from(
                            type = MainScreenListItemDelegateType.CURRENT,
                            forecastWithPlacesAndWinds = forecastWithPlacesAndWinds,
                            resourceManager = resourceManager
                        )
                    )
                    if (it.size > 1) {
                        result.add(TitleMainScreenLisItem(resourceManager.getString(R.string.next_days)))
                    }
                } else {
                    result.add(
                        DayForecastMainScreenListItem.from(
                            type = MainScreenListItemDelegateType.FUTURE,
                            forecastWithPlacesAndWinds = forecastWithPlacesAndWinds,
                            resourceManager = resourceManager
                        )
                    )
                }
            }
            result
        }

    init {
        launchWithProgressInDispatchersIO(hideLoadingStatusWhenDone = true) {
            mainScreenInteractor.requestForecast()
        }
    }
}