package com.example.mooncascadetest.domain.placeorwind

import com.example.mooncascadetest.data.repo.Repository
import javax.inject.Inject

class PlaceOrWindInteractorImpl @Inject constructor(private val repository: Repository) : PlaceOrWindInteractor {

    override suspend fun getPlaceEntityById(placeId: Long) = repository.getPlaceEntityById(placeId)

    override suspend fun getWindEntityById(windId: Long) = repository.getWindEntityById(windId)
}