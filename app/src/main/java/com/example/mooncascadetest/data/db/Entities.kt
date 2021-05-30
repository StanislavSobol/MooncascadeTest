package com.example.mooncascadetest.data.db

import androidx.room.*
import com.example.mooncascadetest.data.api.ForecastModel
import com.example.mooncascadetest.data.api.PlaceModel
import com.example.mooncascadetest.data.api.WindModel
import java.util.*

private const val DAY_PREFIX = "day_"
private const val NIGHT_PREFIX = "night_"
private const val DATE_FIELD = "date"

@Entity
data class ForecastEntity(
    @PrimaryKey
    val date: Date,
    @Embedded(prefix = DAY_PREFIX)
    val day: DayOrNightEntity?,
    @Embedded(prefix = NIGHT_PREFIX)
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

@Entity(
    foreignKeys = [ForeignKey(
        entity = ForecastEntity::class,
        parentColumns = arrayOf(DATE_FIELD),
        childColumns = arrayOf(DATE_FIELD),
        onDelete = ForeignKey.CASCADE
    )]
)
data class PlaceEntity(
    @PrimaryKey
    val placeId: Int,
    val date: Date,
    val isday: Boolean,
    val name: String?,
    val phenomenon: String?,
    val tempmin: Int?,
    val tempmax: Int?
) {
    companion object {
        fun from(placeModel: PlaceModel, placeId: Int, date: Date, isDay: Boolean) = PlaceEntity(
            placeId = placeId,
            date = date,
            isday = isDay,
            name = placeModel.name,
            phenomenon = placeModel.phenomenon,
            tempmin = placeModel.tempmin,
            tempmax = placeModel.tempmax
        )
    }
}

@Entity(
    foreignKeys = [ForeignKey(
        entity = ForecastEntity::class,
        parentColumns = arrayOf(DATE_FIELD),
        childColumns = arrayOf(DATE_FIELD),
        onDelete = ForeignKey.CASCADE
    )]
)
data class WindEntity(
    @PrimaryKey
    val windId: Int,
    val date: Date,
    val isday: Boolean,
    val name: String?,
    val direction: String?,
    val speedmin: Int?,
    val speedmax: Int?
) {
    companion object {
        fun from(windModel: WindModel, windId: Int, date: Date, isDay: Boolean) = WindEntity(
            windId = windId,
            date = date,
            isday = isDay,
            name = windModel.name,
            direction = windModel.direction,
            speedmin = windModel.speedmin,
            speedmax = windModel.speedmax
        )
    }
}

class ForecastWithPlacesAndWinds(@Embedded val forecast: ForecastEntity) {
    @Relation(
        parentColumn = DATE_FIELD,
        entityColumn = DATE_FIELD
    )
    var places: List<PlaceEntity>? = null

    @Relation(
        parentColumn = DATE_FIELD,
        entityColumn = DATE_FIELD
    )
    var winds: List<WindEntity>? = null
}