package com.example.mooncascadetest.tools

import android.content.Context
import com.example.mooncascadetest.R
import com.example.mooncascadetest.tools.resourcemanager.ResourceManager
import com.example.mooncascadetest.tools.resourcemanager.ResourceManagerImpl
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * Unit test for StringUtils.kt
 */
@RunWith(MockitoJUnitRunner::class)
class StringUtilsTest {

    @Mock
    private lateinit var appContext: Context

    private lateinit var resourceManager: ResourceManager

    @Before
    fun setup() {
        resourceManager = ResourceManagerImpl(appContext)
    }

    @Test
    fun `createTempRange ok`() {
        prepareMocksForCreateTempRange()
        val actual = createTempRange(
            MIN_TEMP,
            MAX_TEMP, resourceManager
        )
        TestCase.assertEquals(TEMP_RANGE_WRAPPED, actual)
    }

    @Test
    fun `createTempRange both null`() {
        prepareMocksForCreateTempRange()
        val actual = createTempRange(null, null, resourceManager)
        TestCase.assertEquals(EMPTY_STRING, actual)
    }

    @Test
    fun `createTempRange minTemp is null`() {
        prepareMocksForCreateTempRange()
        val actual = createTempRange(
            null,
            MAX_TEMP, resourceManager
        )
        TestCase.assertEquals(MAX_TEMP_WRAPPED, actual)
    }

    @Test
    fun `createTempRange maxTemp is null`() {
        prepareMocksForCreateTempRange()
        val actual = createTempRange(MIN_TEMP, null, resourceManager)
        TestCase.assertEquals(MIN_TEMP_WRAPPED, actual)
    }

    private fun prepareMocksForCreateTempRange() {
        Mockito.`when`(
            appContext.getString(
                R.string.temp_range,
                TEMP_RANGE_UNWRAPPED
            )
        ).thenReturn(TEMP_RANGE_WRAPPED)
        Mockito.`when`(appContext.getString(R.string.temp_range, MIN_TEMP.toString()))
            .thenReturn(MIN_TEMP_WRAPPED)
        Mockito.`when`(appContext.getString(R.string.temp_range, MAX_TEMP.toString()))
            .thenReturn(MAX_TEMP_WRAPPED)
    }

    private companion object {
        const val MIN_TEMP = -1
        const val MAX_TEMP = 5
        const val TEMP_RANGE_UNWRAPPED = "$MIN_TEMP .. $MAX_TEMP"
        const val TEMP_RANGE_WRAPPED = "$MIN_TEMP .. $MAX_TEMP\u00B0C"
        const val MIN_TEMP_WRAPPED = "$MIN_TEMP\u00B0C"
        const val MAX_TEMP_WRAPPED = "$MAX_TEMP\u00B0C"
    }

}