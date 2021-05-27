package com.example.mooncascadetest.data.api

import com.google.gson.annotations.SerializedName
import java.util.*

data class VehiclesResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("response") val response: List<VehicleItemResponse>
)

data class VehicleItemResponse(
    @SerializedName("objectId") val objectId: Int,
    @SerializedName("plate") val plate: String,
    @SerializedName("driverName") val driverName: String,
    @SerializedName("speed") val speed: Float,
    @SerializedName("address") val address: String,
    @SerializedName("lastEngineOnTime") val lastEngineOnTime: Date?
)

data class VehiclesRawResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("response") val response: List<VehicleRawItemResponse>
)

data class VehicleRawItemResponse(
    @SerializedName("timestamp") val timestamp: Date,
    @SerializedName("Distance") val distance: Float?,
    @SerializedName("DeltaDistance") val deltaDistance: Float?,
    @SerializedName("Longitude") val longitude: Double,
    @SerializedName("Latitude") val latitude: Double
)
