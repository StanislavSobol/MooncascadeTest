package com.example.mooncascadetest.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ForecastEntity(
    @PrimaryKey
    val objectId: Int
) {
    companion object {

//        fun from(vehiclesItemResponse: VehicleItemResponse) = ForecastEntity(
//            objectId = vehiclesItemResponse.objectId,
//            plate = vehiclesItemResponse.plate,
//            driverName = vehiclesItemResponse.driverName,
//            speed = vehiclesItemResponse.speed,
//            address = vehiclesItemResponse.address,
//            lastEngineOnTime = vehiclesItemResponse.lastEngineOnTime
//        )
    }
}