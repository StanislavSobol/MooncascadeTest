package com.example.mooncascadetest.data.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
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
)

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
)

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
)

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