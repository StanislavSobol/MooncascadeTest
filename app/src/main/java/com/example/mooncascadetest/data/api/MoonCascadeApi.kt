package com.example.mooncascadetest.data.api

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET

interface MoonCascadeApi {

    @GET("api/estonia/forecast")
    suspend fun getLastData(): ForecastsResponse
}

data class ForecastsResponse(
    @SerializedName("forecasts") private val forecast: List<ForecastResponse>
)

data class ForecastResponse(
    // TODO to date
    @SerializedName("date") private val date: String,
    @SerializedName("day") private val day: DayOrNight,
    @SerializedName("night") private val night: DayOrNight
)

data class DayOrNight(
    @SerializedName("phenomenon") private val phenomenon: String?,
    @SerializedName("tempmin") private val tempmin: Int?,
    @SerializedName("tempmax") private val tempmax: Int?,
    @SerializedName("text") private val text: String?,
    @SerializedName("sea") private val sea: String?,
    @SerializedName("peipsi") private val peipsi: String?,
    @SerializedName("places") private val places: List<PlaceResponse>?,
    @SerializedName("winds") private val winds: List<WindsResponse>?
)

data class PlaceResponse(
    @SerializedName("name") private val name: String?,
    @SerializedName("phenomenon") private val phenomenon: String?,
    @SerializedName("tempmin") private val tempmin: Int?,
    @SerializedName("tempmax") private val tempmax: Int?
)

data class WindsResponse(
    @SerializedName("name") private val name: String?,
    @SerializedName("direction") private val phenomenon: String?,
    @SerializedName("speedmin") private val tempmin: Int?,
    @SerializedName("speedmax") private val tempmax: Int?
)