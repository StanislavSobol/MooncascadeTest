package com.example.mooncascadetest.domain.mainscreen

import androidx.lifecycle.LiveData
import com.example.mooncascadetest.data.db.ForecastWithPlacesAdWinds

interface MainScreenInteractor {

    fun getForecastLiveData(): LiveData<List<ForecastWithPlacesAdWinds>>

    suspend fun requestForecast()
}