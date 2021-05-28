package com.example.mooncascadetest.presentation.mainscreen

import android.content.Context
import com.example.mooncascadetest.R
import com.example.mooncascadetest.tools.resourcemanager.ResourceManager
import com.example.mooncascadetest.tools.resourcemanager.ResourceManagerImpl
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

/**
 * UNit test for [DayForecastMainScreenListItem]
 */
@RunWith(MockitoJUnitRunner::class)
class DayForecastMainScreenListItemTest {

    private lateinit var dayForecastMainScreenListItem: DayForecastMainScreenListItem

    @Mock
    private lateinit var appContext: Context

    private lateinit var resourceManager: ResourceManager

    @Before
    fun setup() {
        `when`(appContext.getString(R.string.temp_range, TEMP_RANGE_UNWRAPPED)).thenReturn(TEMP_RANGE_WRAPPED)
        `when`(appContext.getString(R.string.temp_range, MIN_TEMP.toString())).thenReturn(MIN_TEMP_WRAPPED)
        `when`(appContext.getString(R.string.temp_range, MAX_TEMP.toString())).thenReturn(MAX_TEMP_WRAPPED)

        resourceManager = ResourceManagerImpl(appContext)

        dayForecastMainScreenListItem = DayForecastMainScreenListItem(
            type = MainScreenListItemDelegateType.CURRENT,
            date = Date(),
            dayPhenomenon = "",
            nightPhenomenon = "",
            dayTempRange = "",
            nightTempRange = "",
            dayText = "",
            nightText = "",
            daySea = "",
            nightSea = "",
            dayPeipsi = "",
            nightPeipsi = ""
        )
    }

    @Test
    fun `createTempRange ok`() {
        val actual = DayForecastMainScreenListItem.createTempRange(MIN_TEMP, MAX_TEMP, resourceManager)
        assertEquals(TEMP_RANGE_WRAPPED, actual)
    }

    @Test
    fun `createTempRange both null`() {
        val actual = DayForecastMainScreenListItem.createTempRange(null, null, resourceManager)
        assertEquals("", actual)
    }

    @Test
    fun `createTempRange minTemp is null`() {
        val actual = DayForecastMainScreenListItem.createTempRange(null, MAX_TEMP, resourceManager)
        assertEquals(MAX_TEMP_WRAPPED, actual)
    }

    @Test
    fun `createTempRange maxTemp is null`() {
        val actual = DayForecastMainScreenListItem.createTempRange(MIN_TEMP, null, resourceManager)
        assertEquals(MIN_TEMP_WRAPPED, actual)
    }

    private companion object {
        const val MIN_TEMP = -1
        const val MAX_TEMP = 5
        const val TEMP_RANGE = "%s \u00B0C"
        const val TEMP_RANGE_UNWRAPPED = "$MIN_TEMP .. $MAX_TEMP"
        const val TEMP_RANGE_WRAPPED = "$MIN_TEMP .. $MAX_TEMP\u00B0C"
        const val MIN_TEMP_WRAPPED = "$MIN_TEMP\u00B0C"
        const val MAX_TEMP_WRAPPED = "$MAX_TEMP\u00B0C"
    }

}