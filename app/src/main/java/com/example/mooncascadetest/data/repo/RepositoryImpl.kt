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

        db.getForecastDao().deleteAllWinds()
        db.getForecastDao().deleteAllPlaces()
        db.getForecastDao().deleteAll()

        api.getForecast().forecast.forEach {
            val forecastEntity = ForecastEntity(
                date = it.date,
                // TODO from
                day = DayOrNightEntity(
                    phenomenon = it.day.phenomenon,
                    tempmin = it.day.tempmin,
                    tempmax = it.day.tempmax,
                    text = it.day.text,
                    sea = it.day.sea,
                    peipsi = it.day.peipsi
                ),
                // TODO from
                night = DayOrNightEntity(
                    phenomenon = it.night.phenomenon,
                    tempmin = it.night.tempmin,
                    tempmax = it.night.tempmax,
                    text = it.night.text,
                    sea = it.night.sea,
                    peipsi = it.night.peipsi
                )
            )

            db.getForecastDao().insert(forecastEntity)

            it.day.places?.forEach { place ->
                // TODO from
                db.getForecastDao().insertPlace(
                    PlaceEntity(
                        date = it.date,
                        isday = true,
                        name = place.name,
                        phenomenon = place.phenomenon,
                        tempmin = place.tempmin,
                        tempmax = place.tempmax
                    )
                )
            }

            it.night.places?.forEach { place ->
                // TODO from
                db.getForecastDao().insertPlace(
                    PlaceEntity(
                        date = it.date,
                        isday = false,
                        name = place.name,
                        phenomenon = place.phenomenon,
                        tempmin = place.tempmin,
                        tempmax = place.tempmax
                    )
                )
            }

            it.day.winds?.forEach { wind ->
                // TODO from
                db.getForecastDao().insertWind(
                    WindEntity(
                        date = it.date,
                        isday = true,
                        name = wind.name,
                        direction = wind.direction,
                        speedmin = wind.speedmin,
                        speedmax = wind.speedmax
                    )
                )
            }

            it.night.winds?.forEach { wind ->
                // TODO from
                db.getForecastDao().insertWind(
                    WindEntity(
                        date = it.date,
                        isday = false,
                        name = wind.name,
                        direction = wind.direction,
                        speedmin = wind.speedmin,
                        speedmax = wind.speedmax
                    )
                )
            }


        }
    }

}