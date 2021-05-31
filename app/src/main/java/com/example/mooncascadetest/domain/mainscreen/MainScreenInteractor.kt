package com.example.mooncascadetest.domain.mainscreen

import androidx.lifecycle.LiveData
import com.example.mooncascadetest.data.db.ForecastWithPlacesAndWindsEntity

interface MainScreenInteractor {

    fun getForecastLiveData(): LiveData<List<ForecastWithPlacesAndWindsEntity>>

    suspend fun requestForecast()
}