package com.example.mooncascadetest.data.repo

interface Repository {
    suspend fun requestForecastAndPlaceToDb()
}