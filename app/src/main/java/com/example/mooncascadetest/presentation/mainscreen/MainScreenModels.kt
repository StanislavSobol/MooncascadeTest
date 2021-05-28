package com.example.mooncascadetest.presentation.mainscreen

import androidx.annotation.VisibleForTesting
import com.example.mooncascadetest.data.db.ForecastWithPlacesAdWinds
import java.util.*

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
    val nightTempRange: String,
    val dayText: String,
    val nightText: String,
    val daySea: String,
    val nightSea: String,
    val dayPeipsi: String,
    val nightPeipsi: String
) : MainScreenListItemDelegate {

    companion object {

        fun from(
            type: MainScreenListItemDelegateType,
            forecastWithPlacesAdWinds: ForecastWithPlacesAdWinds
        ) = DayForecastMainScreenListItem(
            type = type,
            date = forecastWithPlacesAdWinds.forecast.date,
            dayPhenomenon = forecastWithPlacesAdWinds.forecast.day?.phenomenon ?: "",
            nightPhenomenon = forecastWithPlacesAdWinds.forecast.night?.phenomenon ?: "",
            dayTempRange = tempRange(
                forecastWithPlacesAdWinds.forecast.day?.tempmin,
                forecastWithPlacesAdWinds.forecast.day?.tempmax
            ),
            nightTempRange = tempRange(
                forecastWithPlacesAdWinds.forecast.night?.tempmin,
                forecastWithPlacesAdWinds.forecast.night?.tempmax
            ),
            dayText = forecastWithPlacesAdWinds.forecast.day?.text ?: "",
            nightText = forecastWithPlacesAdWinds.forecast.night?.text ?: "",
            daySea = forecastWithPlacesAdWinds.forecast.night?.sea ?: "",
            nightSea = forecastWithPlacesAdWinds.forecast.night?.sea ?: "",
            dayPeipsi = forecastWithPlacesAdWinds.forecast.night?.peipsi ?: "",
            nightPeipsi = forecastWithPlacesAdWinds.forecast.night?.peipsi ?: ""
        )

        @VisibleForTesting
        internal fun tempRange(minTemp: Int?, maxTemp: Int?): String {
            return ""
        }
    }
}

data class TitleMainScreenLisItem(
    val title: String
) : MainScreenListItemDelegate {
    override val type: MainScreenListItemDelegateType
        get() = MainScreenListItemDelegateType.TITLE
}
