package com.example.mooncascadetest.data.repo

import androidx.lifecycle.LiveData
import com.example.mooncascadetest.data.db.ForecastWithPlacesAndWinds

interface Repository {

    fun getForecastLiveData(): LiveData<List<ForecastWithPlacesAndWinds>>

    suspend fun requestForecastAndPlaceToDb()
}