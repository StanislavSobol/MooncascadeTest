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
    @PrimaryKey(autoGenerate = true)
    val placeId: Long = 0L,
    val date: Date,
    val name: String?,
    val dayPhenomenon: String?,
    val dayTempMin: Int?,
    val dayTempMax: Int?,
    val nightPhenomenon: String? = null,
    val nightTempMin: Int? = null,
    val nightTempMax: Int? = null
) {
    companion object {
        /***
         * If night data is absent
         */
        fun fromDay(dayPlaceModel: PlaceModel, date: Date) = PlaceEntity(
            date = date,
            name = dayPlaceModel.name,
            dayPhenomenon = dayPlaceModel.phenomenon,
            dayTempMin = dayPlaceModel.tempmin,
            dayTempMax = dayPlaceModel.tempmax
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
    @PrimaryKey(autoGenerate = true)
    val windId: Long = 0,
    val date: Date,
    val name: String?,
    val dayDirection: String?,
    val daySpeedMin: Int?,
    val daySpeedMax: Int?,
    val nightDirection: String? = null,
    val nightSpeedMin: Int?? = null,
    val nightSpeedMax: Int? = null
) {
    companion object {
        fun fromDay(dayWindModel: WindModel, date: Date) = WindEntity(
            date = date,
            name = dayWindModel.name,
            dayDirection = dayWindModel.direction,
            daySpeedMin = dayWindModel.speedmin,
            daySpeedMax = dayWindModel.speedmax
        )
    }
}

class ForecastWithPlacesAndWindsEntity(@Embedded val forecast: ForecastEntity) {
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