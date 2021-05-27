package com.example.mooncascadetest.data.api

import retrofit2.http.GET

interface MoonCascadeApi {

    @GET("api/estonia/forecast")
    suspend fun getForecast(): ForecastsResponse
}