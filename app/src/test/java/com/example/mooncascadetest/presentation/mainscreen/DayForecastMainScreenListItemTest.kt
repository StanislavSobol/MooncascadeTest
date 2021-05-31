package com.example.mooncascadetest.presentation.mainscreen

import android.content.Context
import android.content.res.Resources
import com.example.mooncascadetest.R
import com.example.mooncascadetest.presentation.mainscreen.model.DayForecastMainScreenListItem
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
    fun `intToWordDegrees 0`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWordDegrees(0, resourceManager)
        assertEquals("zero degrees Celsius", actual)
    }

    @Test
    fun `intToWordDegrees 1`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWordDegrees(1, resourceManager)
        assertEquals("one degree Celsius", actual)
    }

    @Test
    fun `intToWordDegrees -1`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWordDegrees(-1, resourceManager)
        assertEquals("minus one degree Celsius", actual)
    }

    @Test
    fun `intToWordDegrees 5`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWordDegrees(5, resourceManager)
        assertEquals("five degrees Celsius", actual)
    }

    @Test
    fun `intToWordDegrees -7`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWordDegrees(-7, resourceManager)
        assertEquals("minus seven degrees Celsius", actual)
    }

    @Test
    fun `intToWordDegrees 9`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWordDegrees(9, resourceManager)
        assertEquals("nine degrees Celsius", actual)
    }

    @Test
    fun `intToWordDegreesDegrees -8`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWordDegrees(-8, resourceManager)
        assertEquals("minus eight degrees Celsius", actual)
    }

    @Test
    fun `intToWordDegreesDegrees 10`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWordDegrees(10, resourceManager)
        assertEquals("ten degrees Celsius", actual)
    }

    @Test
    fun `intToWordDegreesDegrees 11`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWordDegrees(11, resourceManager)
        assertEquals("eleven degrees Celsius", actual)
    }

    @Test
    fun `intToWordDegreesDegrees -12`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWordDegrees(-12, resourceManager)
        assertEquals("minus twelve degrees Celsius", actual)
    }

    @Test
    fun `intToWordDegreesDegrees 15`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWordDegrees(15, resourceManager)
        assertEquals("fifteen degrees Celsius", actual)
    }

    @Test
    fun `intToWordDegrees -14`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWordDegrees(-14, resourceManager)
        assertEquals("minus fourteen degrees Celsius", actual)
    }

    @Test
    fun `intToWordDegrees 19`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWordDegrees(19, resourceManager)
        assertEquals("nineteen degrees Celsius", actual)
    }

    @Test
    fun `intToWordDegrees -17`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWordDegrees(-17, resourceManager)
        assertEquals("minus seventeen degrees Celsius", actual)
    }

    @Test
    fun `intToWordDegrees 20`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWordDegrees(20, resourceManager)
        assertEquals("twenty degrees Celsius", actual)
    }

    @Test
    fun `intToWordDegrees -20`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWordDegrees(-20, resourceManager)
        assertEquals("minus twenty degrees Celsius", actual)
    }

    @Test
    fun `intToWordDegrees 31`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWordDegrees(31, resourceManager)
        assertEquals("thirty one degrees Celsius", actual)
    }

    @Test
    fun `intToWordDegrees -45`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWordDegrees(-45, resourceManager)
        assertEquals("minus forty five degrees Celsius", actual)
    }

    @Test
    fun `intToWordDegrees 50`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWordDegrees(50, resourceManager)
        assertEquals("fifty degrees Celsius", actual)
    }

    @Test
    fun `intToWordDegrees -78`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWordDegrees(-78, resourceManager)
        assertEquals("minus seventy eight degrees Celsius", actual)
    }

    @Test
    fun `intToWordDegrees 99`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWordDegrees(99, resourceManager)
        assertEquals("ninety nine degrees Celsius", actual)
    }

    @Test
    fun `intToWordDegrees -99`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWordDegrees(-99, resourceManager)
        assertEquals("minus ninety nine degrees Celsius", actual)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `intToWordDegrees 100`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWordDegrees(100, resourceManager)
        assertEquals("minus seventy eight degrees Celsius", actual)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `intToWordDegrees -100`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWordDegrees(-100, resourceManager)
        assertEquals("minus seventy eight degrees Celsius", actual)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `intToWordDegrees 1234`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWordDegrees(1234, resourceManager)
        assertEquals("minus seventy eight degrees Celsius", actual)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `intToWordDegrees -258`() {
        prepareMocksForIntToWord()
        val actual = DayForecastMainScreenListItem.intToWordDegrees(-258, resourceManager)
        assertEquals("minus seventy eight degrees Celsius", actual)
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
    }
}