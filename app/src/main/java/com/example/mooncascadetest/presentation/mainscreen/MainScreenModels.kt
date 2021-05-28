package com.example.mooncascadetest.presentation.mainscreen

import androidx.annotation.VisibleForTesting
import com.example.mooncascadetest.R
import com.example.mooncascadetest.data.db.ForecastWithPlacesAndWinds
import com.example.mooncascadetest.tools.EMPTY_STRING
import com.example.mooncascadetest.tools.resourcemanager.ResourceManager
import java.util.*
import kotlin.math.absoluteValue

// TODO Models to diff files
interface MainScreenListItemDelegate {
    val type: MainScreenListItemDelegateType
}

enum class MainScreenListItemDelegateType(val type: Int) {
    CURRENT(0), FUTURE(1), TITLE(2)
}

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
            forecastWithPlacesAndWinds: ForecastWithPlacesAndWinds,
            resourceManager: ResourceManager
        ) = DayForecastMainScreenListItem(
            type = type,
            date = forecastWithPlacesAndWinds.forecast.date,
            dayPhenomenon = forecastWithPlacesAndWinds.forecast.day?.phenomenon ?: EMPTY_STRING,
            nightPhenomenon = forecastWithPlacesAndWinds.forecast.night?.phenomenon ?: EMPTY_STRING,
            dayTempRange = createTempRange(
                forecastWithPlacesAndWinds.forecast.day?.tempmin,
                forecastWithPlacesAndWinds.forecast.day?.tempmax,
                resourceManager
            ),
            dayMinTempWords = createMinTempWord(forecastWithPlacesAndWinds.forecast.day?.tempmin, resourceManager),
            dayMaxTempWords = createMaxTempWord(forecastWithPlacesAndWinds.forecast.day?.tempmax, resourceManager),
            nightTempRange = createTempRange(
                forecastWithPlacesAndWinds.forecast.night?.tempmin,
                forecastWithPlacesAndWinds.forecast.night?.tempmax,
                resourceManager
            ),
            nightMinTempWords = createMinTempWord(forecastWithPlacesAndWinds.forecast.night?.tempmin, resourceManager),
            nightMaxTempWords = createMaxTempWord(forecastWithPlacesAndWinds.forecast.night?.tempmax, resourceManager),
            dayText = forecastWithPlacesAndWinds.forecast.day?.text ?: EMPTY_STRING,
            nightText = forecastWithPlacesAndWinds.forecast.night?.text ?: EMPTY_STRING,
            daySea = forecastWithPlacesAndWinds.forecast.night?.sea ?: EMPTY_STRING,
            nightSea = forecastWithPlacesAndWinds.forecast.night?.sea ?: EMPTY_STRING,
            dayPeipsi = forecastWithPlacesAndWinds.forecast.night?.peipsi ?: EMPTY_STRING,
            nightPeipsi = forecastWithPlacesAndWinds.forecast.night?.peipsi ?: EMPTY_STRING,
            isPlacesAndWindsExist = !forecastWithPlacesAndWinds.places.isNullOrEmpty() &&
                    !forecastWithPlacesAndWinds.winds.isNullOrEmpty()
        )

        @VisibleForTesting
        internal fun createTempRange(minTemp: Int?, maxTemp: Int?, resourceManager: ResourceManager): String {
            if (minTemp == null && maxTemp == null) {
                return EMPTY_STRING
            }

            if (minTemp != null && maxTemp != null) {
                val value = "$minTemp .. $maxTemp"
                return resourceManager.getString(R.string.temp_range, value)
            }

            return when {
                minTemp != null -> resourceManager.getString(R.string.temp_range, minTemp.toString())
                maxTemp != null -> resourceManager.getString(R.string.temp_range, maxTemp.toString())
                else -> throw IllegalStateException()
            }
        }

        private fun createMinTempWord(temp: Int?, resourceManager: ResourceManager): String {
            return resourceManager.getString(R.string.min_temp_words, intToWord(temp, resourceManager))
        }

        private fun createMaxTempWord(temp: Int?, resourceManager: ResourceManager): String {
            return resourceManager.getString(R.string.max_temp_words, intToWord(temp, resourceManager))
        }

        @VisibleForTesting
        internal fun intToWord(value: Int?, resourceManager: ResourceManager): String {
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

data class TitleMainScreenLisItem(
    val title: String
) : MainScreenListItemDelegate {
    override val type: MainScreenListItemDelegateType
        get() = MainScreenListItemDelegateType.TITLE
}
