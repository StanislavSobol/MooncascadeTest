package com.example.mooncascadetest.presentation.mainscreen

import android.content.Context
import android.content.res.Resources
import com.example.mooncascadetest.R
import com.example.mooncascadetest.tools.EMPTY_STRING
import com.example.mooncascadetest.tools.resourcemanager.ResourceManager
import com.example.mooncascadetest.tools.resourcemanager.ResourceManagerImpl
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

/**
 * UNit test for [DayForecastMainScreenListItem]
 */
@RunWith(MockitoJUnitRunner::class)
class DayForecastMainScreenListItemTest {

    @Mock
    private lateinit var appContext: Context

    @Mock
    private lateinit var resources: Resources

    private lateinit var resourceManager: ResourceManager

    @Before
    fun setup() {
        resourceManager = ResourceManagerImpl(appContext)
    }

    @Test
    fun `createTempRange ok`() {
        prepareMocksForCreateTempRange()
        val actual = DayForecastMainScreenListItem.createTempRange(MIN_TEMP, MAX_TEMP, resourceManager)
        assertEquals(TEMP_RANGE_WRAPPED, actual)
    }

    @Test
    fun `createTempRange both null`() {
        prepareMocksForCreateTempRange()
        val actual = DayForecastMainScreenListItem.createTempRange(null, null, resourceManager)
        assertEquals(EMPTY_STRING, actual)
    }

    @Test
    fun `createTempRange minTemp is null`() {
        prepareMocksForCreateTempRange()
        val actual = DayForecastMainScreenListItem.createTempRange(null, MAX_TEMP, resourceManager)
        assertEquals(MAX_TEMP_WRAPPED, actual)
    }

    @Test
    fun `createTempRange maxTemp is null`() {
        prepareMocksForCreateTempRange()
        val actual = DayForecastMainScreenListItem.createTempRange(MIN_TEMP, null, resourceManager)
        assertEquals(MIN_TEMP_WRAPPED, actual)
    }

    @Test
    fun `intToWord 0`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWord(0, resourceManager)
        assertEquals("zero degrees Celsius", actual)
    }

    @Test
    fun `intToWord 1`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWord(1, resourceManager)
        assertEquals("one degree Celsius", actual)
    }

    @Test
    fun `intToWord -1`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWord(-1, resourceManager)
        assertEquals("minus one degree Celsius", actual)
    }

    @Test
    fun `intToWord 5`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWord(5, resourceManager)
        assertEquals("five degrees Celsius", actual)
    }

    @Test
    fun `intToWord -7`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWord(-7, resourceManager)
        assertEquals("minus seven degrees Celsius", actual)
    }

    @Test
    fun `intToWord 9`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWord(9, resourceManager)
        assertEquals("nine degrees Celsius", actual)
    }

    @Test
    fun `intToWord -8`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWord(-8, resourceManager)
        assertEquals("minus eight degrees Celsius", actual)
    }

    @Test
    fun `intToWord 10`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWord(10, resourceManager)
        assertEquals("ten degrees Celsius", actual)
    }

    @Test
    fun `intToWord 11`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWord(11, resourceManager)
        assertEquals("eleven degrees Celsius", actual)
    }

    @Test
    fun `intToWord -12`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWord(-12, resourceManager)
        assertEquals("minus twelve degrees Celsius", actual)
    }

    @Test
    fun `intToWord 15`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWord(15, resourceManager)
        assertEquals("fifteen degrees Celsius", actual)
    }

    @Test
    fun `intToWord -14`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWord(-14, resourceManager)
        assertEquals("minus fourteen degrees Celsius", actual)
    }

    @Test
    fun `intToWord 19`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWord(19, resourceManager)
        assertEquals("nineteen degrees Celsius", actual)
    }

    @Test
    fun `intToWord -17`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWord(-17, resourceManager)
        assertEquals("minus seventeen degrees Celsius", actual)
    }

    @Test
    fun `intToWord 20`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWord(20, resourceManager)
        assertEquals("twenty degrees Celsius", actual)
    }

    @Test
    fun `intToWord -20`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWord(-20, resourceManager)
        assertEquals("minus twenty degrees Celsius", actual)
    }

    @Test
    fun `intToWord 31`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWord(31, resourceManager)
        assertEquals("thirty one degrees Celsius", actual)
    }

    @Test
    fun `intToWord -45`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWord(-45, resourceManager)
        assertEquals("minus forty five degrees Celsius", actual)
    }

    @Test
    fun `intToWord 50`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWord(50, resourceManager)
        assertEquals("fifty degrees Celsius", actual)
    }

    @Test
    fun `intToWord -78`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWord(-78, resourceManager)
        assertEquals("minus seventy eight degrees Celsius", actual)
    }

    @Test
    fun `intToWord 99`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWord(99, resourceManager)
        assertEquals("ninety nine degrees Celsius", actual)
    }

    @Test
    fun `intToWord -99`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWord(-99, resourceManager)
        assertEquals("minus ninety nine degrees Celsius", actual)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `intToWord 100`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWord(100, resourceManager)
        assertEquals("minus seventy eight degrees Celsius", actual)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `intToWord -100`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWord(-100, resourceManager)
        assertEquals("minus seventy eight degrees Celsius", actual)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `intToWord 1234`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWord(1234, resourceManager)
        assertEquals("minus seventy eight degrees Celsius", actual)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `intToWord -258`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWord(-258, resourceManager)
        assertEquals("minus seventy eight degrees Celsius", actual)
    }


    private fun prepareMocksForCreateTempRange() {
        `when`(appContext.getString(R.string.temp_range, TEMP_RANGE_UNWRAPPED)).thenReturn(TEMP_RANGE_WRAPPED)
        `when`(appContext.getString(R.string.temp_range, MIN_TEMP.toString())).thenReturn(MIN_TEMP_WRAPPED)
        `when`(appContext.getString(R.string.temp_range, MAX_TEMP.toString())).thenReturn(MAX_TEMP_WRAPPED)
    }

    private fun prepareMocksForIntToWord() {
        `when`(appContext.resources).thenReturn(resources)
        `when`(resources.getStringArray(R.array.digits_arr)).thenReturn(
            arrayOf(
                "zero",
                "one",
                "two",
                "three",
                "four",
                "five",
                "six",
                "seven",
                "eight",
                "nine"
            )
        )
        `when`(resources.getStringArray(R.array.second_dec_arr)).thenReturn(
            arrayOf(
                "ten",
                "eleven",
                "twelve",
                "thirteen",
                "fourteen",
                "fifteen",
                "sixteen",
                "seventeen",
                "eighteen",
                "nineteen"
            )
        )
        `when`(resources.getStringArray(R.array.decs_arr)).thenReturn(
            arrayOf(
                "ten",
                "twenty",
                "thirty",
                "forty",
                "fifty",
                "sixty",
                "seventy",
                "eighty",
                "ninety"
            )
        )
        `when`(appContext.getString(R.string.one_degree)).thenReturn("degree Celsius")
        `when`(appContext.getString(R.string.plural_degrees)).thenReturn("degrees Celsius")
        `when`(appContext.getString(R.string.minus)).thenReturn("minus")
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