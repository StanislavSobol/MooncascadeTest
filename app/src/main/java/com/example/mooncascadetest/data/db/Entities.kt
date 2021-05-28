package com.example.mooncascadetest.data.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.mooncascadetest.data.api.ForecastModel
import com.example.mooncascadetest.data.api.PlaceModel
import com.example.mooncascadetest.data.api.WindModel
import java.util.*

@Entity
data class ForecastEntity(
    @PrimaryKey
    val date: Date,
    @Embedded(prefix = "day_")
    val day: DayOrNightEntity?,
    @Embedded(prefix = "night_")
    val night: DayOrNightEntity?
)

data class DayOrNightEntity(
    val phenomenon: String?,
    val tempmin: Int?,
    val tempmax: Int?,
    val text: String?,
    val sea: String?,
    val peipsi: String?
) {
    companion object {
        fun from(forecastModel: ForecastModel, isDay: Boolean): DayOrNightEntity {
            val dayOrNightModel = if (isDay) forecastModel.day else forecastModel.night
            return DayOrNightEntity(
                phenomenon = dayOrNightModel.phenomenon,
                tempmin = dayOrNightModel.tempmin,
                tempmax = dayOrNightModel.tempmax,
                text = dayOrNightModel.text,
                sea = dayOrNightModel.sea,
                peipsi = dayOrNightModel.peipsi
            )
        }
    }
}

@Entity
data class PlaceEntity(
    @PrimaryKey(autoGenerate = true)
    val placeId: Int = 0,
    // FK
    val date: Date,
    val isday: Boolean,
    val name: String?,
    val phenomenon: String?,
    val tempmin: Int?,
    val tempmax: Int?
) {
    companion object {
        fun from(placeModel: PlaceModel, date: Date, isDay: Boolean) = PlaceEntity(
            date = date,
            isday = isDay,
            name = placeModel.name,
            phenomenon = placeModel.phenomenon,
            tempmin = placeModel.tempmin,
            tempmax = placeModel.tempmax
        )
    }
}

@Entity
data class WindEntity(
    @PrimaryKey(autoGenerate = true)
    val windId: Int = 0,
    // FK
    val date: Date,
    val isday: Boolean,
    val name: String?,
    val direction: String?,
    val speedmin: Int?,
    val speedmax: Int?
) {
    companion object {
        fun from(windModel: WindModel, date: Date, isDay: Boolean) = WindEntity(
            date = date,
            isday = isDay,
            name = windModel.name,
            direction = windModel.direction,
            speedmin = windModel.speedmin,
            speedmax = windModel.speedmax
        )
    }
}

class ForecastWithPlacesAdWinds(@Embedded val forecast: ForecastEntity) {
    @Relation(
        parentColumn = "date",
        entityColumn = "date"
    )
    var places: List<PlaceEntity>? = null

    @Relation(
        parentColumn = "date",
        entityColumn = "date"
    )
    var winds: List<WindEntity>? = null
}