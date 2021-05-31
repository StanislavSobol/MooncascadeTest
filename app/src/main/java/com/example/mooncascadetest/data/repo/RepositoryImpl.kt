package com.example.mooncascadetest.data.repo

import com.example.mooncascadetest.R
import com.example.mooncascadetest.data.api.ForecastModel
import com.example.mooncascadetest.data.api.MoonCascadeApi
import com.example.mooncascadetest.data.db.*
import com.example.mooncascadetest.tools.MoonCascadeException
import com.example.mooncascadetest.tools.onlinenfoprovider.OnlineInfoProvider
import com.example.mooncascadetest.tools.resourcemanager.ResourceManager
import java.util.*

class RepositoryImpl(
    private val db: MoonCascadeDatabase,
    private val api: MoonCascadeApi,
    private val resourceManager: ResourceManager,
    private val onlineInfoProvider: OnlineInfoProvider
) : Repository {

    override fun getForecastLiveData() = db.getForecastDao().selectAll()

    override suspend fun requestForecastAndPlaceToDb() {
        if (!onlineInfoProvider.isOnline) {
            throw MoonCascadeException(resourceManager.getString(R.string.offline))
        }

        val forecastEntities = mutableListOf<ForecastEntity>()
        val placesEntities = mutableListOf<PlaceEntity>()
        val windsEntities = mutableListOf<WindEntity>()

        api.getForecast().forecast.forEach {
            val forecastEntity = ForecastEntity(
                date = it.date,
                day = DayOrNightEntity.from(forecastModel = it, isDay = true),
                night = DayOrNightEntity.from(forecastModel = it, isDay = false)
            )

            forecastEntities.add(forecastEntity)
            placesEntities.addAll(createPlacesEntitiesFromForecastModel(it))
            windsEntities.addAll(createWindsEntitiesFromForecastModel(it))
        }

        with(db) {
            getWindDao().deleteAll()
            getPlaceDao().deleteAll()
            getForecastDao().deleteAll()

            getForecastDao().insertAll(forecastEntities)
            getPlaceDao().insertAll(placesEntities)
            getWindDao().insertAll(windsEntities)
        }
    }

    private fun createPlacesEntitiesFromForecastModel(forecastModel: ForecastModel): List<PlaceEntity> {
        val result = mutableListOf<PlaceEntity>()
        forecastModel.day.places?.forEach { dayPlace ->
            var placeEntity: PlaceEntity? = null
            forecastModel.night.places?.find { item -> dayPlace.name == item.name }?.let { foundNightItem ->
                placeEntity = PlaceEntity(
                    date = forecastModel.date,
                    name = dayPlace.name,
                    dayPhenomenon = dayPlace.phenomenon,
                    dayTempMin = dayPlace.tempmin,
                    dayTempMax = dayPlace.tempmax,
                    nightPhenomenon = foundNightItem.phenomenon,
                    nightTempMin = foundNightItem.tempmin,
                    nightTempMax = foundNightItem.tempmax
                )
            } ?: run {
                placeEntity = PlaceEntity.fromDay(dayPlace, forecastModel.date)
            }

            placeEntity?.let { item -> result.add(item) }
        }
        return result
    }

    private fun createWindsEntitiesFromForecastModel(forecastModel: ForecastModel): List<WindEntity> {
        val result = mutableListOf<WindEntity>()
        forecastModel.day.winds?.forEach { dayWind ->
            var windEntity: WindEntity? = null
            forecastModel.night.winds?.find { item -> dayWind.name == item.name }?.let { foundNightItem ->
                windEntity = WindEntity(
                    date = forecastModel.date,
                    name = dayWind.name,
                    dayDirection = dayWind.direction,
                    daySpeedMin = dayWind.speedmin,
                    daySpeedMax = dayWind.speedmax,
                    nightDirection = foundNightItem.direction,
                    nightSpeedMin = foundNightItem.speedmin,
                    nightSpeedMax = foundNightItem.speedmax
                )
            } ?: run {
                windEntity = WindEntity.fromDay(dayWind, forecastModel.date)
            }

            windEntity?.let { item -> result.add(item) }
        }
        return result
    }

    override suspend fun getPlacesForDate(date: Date) = db.getPlaceDao().selectForDate(date)

    override suspend fun getWindsForDate(date: Date) = db.getWindDao().selectForDate(date)

    override suspend fun getPlaceEntityById(placeId: Long) = db.getPlaceDao().getByPlaceId(placeId)

    override suspend fun getWindEntityById(windId: Long) = db.getWindDao().getByWindId(windId)
}