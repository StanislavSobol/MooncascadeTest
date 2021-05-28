package com.example.mooncascadetest.domain.mainscreen

import androidx.lifecycle.LiveData
import com.example.mooncascadetest.data.db.ForecastWithPlacesAndWinds

interface MainScreenInteractor {

    fun getForecastLiveData(): LiveData<List<ForecastWithPlacesAndWinds>>

    suspend fun requestForecast()
}