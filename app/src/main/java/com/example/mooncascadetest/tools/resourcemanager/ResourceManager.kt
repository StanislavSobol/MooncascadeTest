package com.example.mooncascadetest.tools.resourcemanager

import androidx.annotation.ArrayRes
import androidx.annotation.StringRes

interface ResourceManager {

    fun getString(@StringRes resId: Int): String

    fun getString(@StringRes resId: Int, subStr: String): String

    fun getStringArray(@ArrayRes resId: Int): Array<out String>
}