package com.example.mooncascadetest.presentation.mainscreen.model

import androidx.annotation.VisibleForTesting
import com.example.mooncascadetest.R
import com.example.mooncascadetest.data.db.ForecastWithPlacesAndWindsEntity
import com.example.mooncascadetest.tools.EMPTY_STRING
import com.example.mooncascadetest.tools.createTempRange
import com.example.mooncascadetest.tools.resourcemanager.ResourceManager
import java.util.*
import kotlin.math.absoluteValue

data class DayForecastMainScreenListItem(
    override val type: MainScreenListItemDelegateType,
    val date: Date,
    val dayPhenomenon: String,
    val nightPhenomenon: String,
    val dayTempRange: String,
    val dayMinTempWords: String,
    val dayMaxTempWords: String,
    val nightTempRange: String,
    val nightMinTempWords: String,
    val nightMaxTempWords: String,
    val dayText: String,
    val nightText: String,
    val daySea: String,
    val nightSea: String,
    val dayPeipsi: String,
    val nightPeipsi: String,
    val isPlacesAndWindsExist: Boolean
) : MainScreenListItemDelegate {

    companion object {

        fun from(
            type: MainScreenListItemDelegateType,
            forecastWithPlacesAndWindsEntity: ForecastWithPlacesAndWindsEntity,
            resourceManager: ResourceManager
        ) = DayForecastMainScreenListItem(
            type = type,
            date = forecastWithPlacesAndWindsEntity.forecast.date,
            dayPhenomenon = forecastWithPlacesAndWindsEntity.forecast.day?.phenomenon ?: EMPTY_STRING,
            nightPhenomenon = forecastWithPlacesAndWindsEntity.forecast.night?.phenomenon ?: EMPTY_STRING,
            dayTempRange = createTempRange(
                forecastWithPlacesAndWindsEntity.forecast.day?.tempmin,
                forecastWithPlacesAndWindsEntity.forecast.day?.tempmax,
                resourceManager
            ),
            dayMinTempWords = createMinTempWord(
                forecastWithPlacesAndWindsEntity.forecast.day?.tempmin,
                resourceManager
            ),
            dayMaxTempWords = createMaxTempWord(
                forecastWithPlacesAndWindsEntity.forecast.day?.tempmax,
                resourceManager
            ),
            nightTempRange = createTempRange(
                forecastWithPlacesAndWindsEntity.forecast.night?.tempmin,
                forecastWithPlacesAndWindsEntity.forecast.night?.tempmax,
                resourceManager
            ),
            nightMinTempWords = createMinTempWord(
                forecastWithPlacesAndWindsEntity.forecast.night?.tempmin,
                resourceManager
            ),
            nightMaxTempWords = createMaxTempWord(
                forecastWithPlacesAndWindsEntity.forecast.night?.tempmax,
                resourceManager
            ),
            dayText = forecastWithPlacesAndWindsEntity.forecast.day?.text ?: EMPTY_STRING,
            nightText = forecastWithPlacesAndWindsEntity.forecast.night?.text ?: EMPTY_STRING,
            daySea = forecastWithPlacesAndWindsEntity.forecast.night?.sea ?: EMPTY_STRING,
            nightSea = forecastWithPlacesAndWindsEntity.forecast.night?.sea ?: EMPTY_STRING,
            dayPeipsi = forecastWithPlacesAndWindsEntity.forecast.night?.peipsi ?: EMPTY_STRING,
            nightPeipsi = forecastWithPlacesAndWindsEntity.forecast.night?.peipsi ?: EMPTY_STRING,
            isPlacesAndWindsExist = !forecastWithPlacesAndWindsEntity.places.isNullOrEmpty() &&
                    !forecastWithPlacesAndWindsEntity.winds.isNullOrEmpty()
        )

        private fun createMinTempWord(temp: Int?, resourceManager: ResourceManager): String {
            return resourceManager.getString(
                R.string.min_temp_words,
                intToWordDegrees(
                    temp,
                    resourceManager
                )
            )
        }

        private fun createMaxTempWord(temp: Int?, resourceManager: ResourceManager): String {
            return resourceManager.getString(
                R.string.max_temp_words,
                intToWordDegrees(
                    temp,
                    resourceManager
                )
            )
        }

        @VisibleForTesting
        internal fun intToWordDegrees(value: Int?, resourceManager: ResourceManager): String {
            value ?: return EMPTY_STRING
            if (value < -99 || value > 99) {
                throw IllegalArgumentException("The value must be between -99 and 99")
            }

            val minus = if (value < 0) resourceManager.getString(R.string.minus) else EMPTY_STRING

            val valueAbs = value.absoluteValue

            val words = when {
                valueAbs < 10 -> resourceManager.getStringArray(R.array.digits_arr)[valueAbs]
                valueAbs < 20 -> resourceManager.getStringArray(R.array.second_dec_arr)[valueAbs - 10]
                else -> {
                    val dig0 = Integer.parseInt(valueAbs.toString()[0].toString())
                    val dig1 = Integer.parseInt(valueAbs.toString()[1].toString())
                    if (dig1 == 0) {
                        resourceManager.getStringArray(R.array.decs_arr)[dig0 - 1]
                    } else {
                        resourceManager.getStringArray(R.array.decs_arr)[dig0 - 1] + " " +
                                resourceManager.getStringArray(R.array.digits_arr)[dig1]
                    }
                }
            }

            // I do not use "%d" in string because it is more convenient to test
            val degrees = if (valueAbs == 1) {
                resourceManager.getString(R.string.one_degree)
            } else {
                resourceManager.getString(R.string.plural_degrees)
            }

            return "$minus $words $degrees".trim()
        }
    }
}