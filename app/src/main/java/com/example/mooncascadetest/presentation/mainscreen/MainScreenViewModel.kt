package com.example.mooncascadetest.presentation.mainscreen

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.mooncascadetest.R
import com.example.mooncascadetest.domain.mainscreen.MainScreenInteractor
import com.example.mooncascadetest.presentation.BaseViewModel
import com.example.mooncascadetest.tools.OneShotEvent
import com.example.mooncascadetest.tools.resourcemanager.ResourceManager
import java.util.*
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
    private val mainScreenInteractor: MainScreenInteractor,
    private val resourceManager: ResourceManager
) : BaseViewModel() {

    val forecastLiveData: LiveData<List<MainScreenListItemDelegate>> = getTransformedLiveData()

    private val _toPlacesAndWindsEvent = MutableLiveData<OneShotEvent<Date>>()
    val toPlacesAndWindsEvent: LiveData<OneShotEvent<Date>>
        get() = _toPlacesAndWindsEvent

    init {
        launchWithProgressInDispatchersIO(hideLoadingStatusWhenDone = true) {
            mainScreenInteractor.requestForecast()
        }
    }

    @MainThread
    fun onPlacesAndWindsClicked(date: Date) {
        _toPlacesAndWindsEvent.value = OneShotEvent(date)
    }

    private fun getTransformedLiveData(): LiveData<List<MainScreenListItemDelegate>> {
        return Transformations.map(mainScreenInteractor.getForecastLiveData()) {
            val result = mutableListOf<MainScreenListItemDelegate>()
            it.forEachIndexed { index, forecastWithPlacesAndWinds ->
                if (index == 0) {
                    result.add(TitleMainScreenLisItem(resourceManager.getString(R.string.today)))
                    result.add(
                        DayForecastMainScreenListItem.from(
                            type = MainScreenListItemDelegateType.CURRENT,
                            forecastWithPlacesAndWindsEntity = forecastWithPlacesAndWinds,
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
                            forecastWithPlacesAndWindsEntity = forecastWithPlacesAndWinds,
                            resourceManager = resourceManager
                        )
                    )
                }
            }
            result
        }
    }
}