package com.example.mooncascadetest.data.api

import com.google.gson.annotations.SerializedName
import java.util.*

data class ForecastsResponse(
    @SerializedName("forecasts") val forecast: List<ForecastModel>
)

data class ForecastModel(
    @SerializedName("date") val date: Date,
    @SerializedName("day") val day: DayOrNightModel,
    @SerializedName("night") val night: DayOrNightModel
)

data class DayOrNightModel(
    @SerializedName("phenomenon") val phenomenon: String?,
    @SerializedName("tempmin") val tempmin: Int?,
    @SerializedName("tempmax") val tempmax: Int?,
    @SerializedName("text") val text: String?,
    @SerializedName("sea") val sea: String?,
    @SerializedName("peipsi") val peipsi: String?,
    @SerializedName("places") val places: List<PlaceModel>?,
    @SerializedName("winds") val winds: List<WindModel>?
)

data class PlaceModel(
    @SerializedName("name") val name: String?,
    @SerializedName("phenomenon") val phenomenon: String?,
    @SerializedName("tempmin") val tempmin: Int?,
    @SerializedName("tempmax") val tempmax: Int?
)

data class WindModel(
    @SerializedName("name") val name: String?,
    @SerializedName("direction") val direction: String?,
    @SerializedName("speedmin") val speedmin: Int?,
    @SerializedName("speedmax") val speedmax: Int?
)