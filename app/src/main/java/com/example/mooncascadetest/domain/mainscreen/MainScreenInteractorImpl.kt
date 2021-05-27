package com.example.mooncascadetest.domain.mainscreen

import com.example.mooncascadetest.data.repo.Repository
import javax.inject.Inject

class MainScreenInteractorImpl @Inject constructor(private val repository: Repository) : MainScreenInteractor {

    override suspend fun requestForecast() {
        repository.requestForecastAndPlaceToDb()
    }
}