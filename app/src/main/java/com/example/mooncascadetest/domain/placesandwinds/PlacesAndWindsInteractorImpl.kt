package com.example.mooncascadetest.domain.placesandwinds

import com.example.mooncascadetest.data.repo.Repository
import java.util.*
import javax.inject.Inject

class PlacesAndWindsInteractorImpl @Inject constructor(private val repository: Repository) : PlacesAndWindsInteractor {

    override fun getPlacesForDateLiveData(date: Date) = repository.getPlacesForDateLiveData(date)

    override fun getWindsForDateLiveData(date: Date) = repository.getWindsForDateLiveData(date)

    override suspend fun getPlacesForDate(date: Date) = repository.getPlacesForDate(date)

    override suspend fun getWindsForDate(date: Date) = repository.getWindsForDate(date)
}