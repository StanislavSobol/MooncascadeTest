package com.example.mooncascadetest.data.repo

import com.example.mooncascadetest.R
import com.example.mooncascadetest.data.api.MoonCascadeApi
import com.example.mooncascadetest.data.db.*
import com.example.mooncascadetest.tools.MoonCascadeException
import com.example.mooncascadetest.tools.onlinenfoprovider.OnlineInfoProvider
import com.example.mooncascadetest.tools.resourcemanager.ResourceManager

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

        db.getWindDao().deleteAll()
        db.getPlaceDao().deleteAll()
        db.getForecastDao().deleteAll()

        api.getForecast().forecast.forEach {
            val forecastEntity = ForecastEntity(
                date = it.date,
                day = DayOrNightEntity.from(forecastModel = it, isDay = true),
                night = DayOrNightEntity.from(forecastModel = it, isDay = false)
            )

            db.getForecastDao().insert(forecastEntity)

            it.day.places?.forEach { place ->
                db.getPlaceDao().insert(PlaceEntity.from(placeModel = place, date = it.date, isDay = true))
            }

            it.night.places?.forEach { place ->
                db.getPlaceDao().insert(PlaceEntity.from(placeModel = place, date = it.date, isDay = false))
            }

            it.day.winds?.forEach { wind ->
                db.getWindDao().insert(WindEntity.from(windModel = wind, date = it.date, isDay = true))
            }

            it.night.winds?.forEach { wind ->
                db.getWindDao().insert(WindEntity.from(windModel = wind, date = it.date, isDay = false))
            }
        }
    }
}