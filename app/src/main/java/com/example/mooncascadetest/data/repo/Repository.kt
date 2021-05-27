package com.example.mooncascadetest.data.repo

import androidx.lifecycle.LiveData
import com.example.mooncascadetest.data.db.ForecastWithPlacesAdWinds

interface Repository {

    fun getForecastLiveData(): LiveData<List<ForecastWithPlacesAdWinds>>

    suspend fun requestForecastAndPlaceToDb()
}