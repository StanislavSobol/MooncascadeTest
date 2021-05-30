package com.example.mooncascadetest.data.repo

import com.example.mooncascadetest.R
import com.example.mooncascadetest.data.api.MoonCascadeApi
import com.example.mooncascadetest.data.api.PlaceModel
import com.example.mooncascadetest.data.api.WindModel
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

            placesEntities.addAll(createAllPlaceEntities(places = it.day.places, date = it.date, isDay = true))
            placesEntities.addAll(createAllPlaceEntities(places = it.night.places, date = it.date, isDay = false))

            windsEntities.addAll(createAllWinEntities(winds = it.day.winds, date = it.date, isDay = true))
            windsEntities.addAll(createAllWinEntities(winds = it.night.winds, date = it.date, isDay = false))
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

    private fun createAllPlaceEntities(places: List<PlaceModel>?, date: Date, isDay: Boolean): List<PlaceEntity> {
        val result = mutableListOf<PlaceEntity>()
        places?.forEachIndexed { index, place ->
            result.add(PlaceEntity.from(placeModel = place, placeId = index, date = date, isDay = isDay))
        }
        return result
    }

    private fun createAllWinEntities(winds: List<WindModel>?, date: Date, isDay: Boolean): List<WindEntity> {
        val result = mutableListOf<WindEntity>()
        winds?.forEachIndexed { index, wind ->
            result.add(WindEntity.from(windModel = wind, windId = index, date = date, isDay = isDay))
        }
        return result
    }
}