package com.example.mooncascadetest.tools

import androidx.annotation.StringRes
import com.example.mooncascadetest.R
import com.example.mooncascadetest.tools.resourcemanager.ResourceManager

fun createTempRange(min: Int?, max: Int?, resourceManager: ResourceManager): String {
    return createRange(min, max, resourceManager, R.string.temp_range)
}

fun createWindRange(min: Int?, max: Int?, resourceManager: ResourceManager): String {
    return createRange(min, max, resourceManager, R.string.wind_range)
}

private fun createRange(min: Int?, max: Int?, resourceManager: ResourceManager, @StringRes stringRes: Int): String {
    if (min == null && max == null) {
        return EMPTY_STRING
    }

    if (min != null && max != null) {
        val value = "$min .. $max"
        return resourceManager.getString(stringRes, value)
    }

    return when {
        min != null -> resourceManager.getString(stringRes, min.toString())
        max != null -> resourceManager.getString(stringRes, max.toString())
        else -> throw IllegalStateException()
    }
}