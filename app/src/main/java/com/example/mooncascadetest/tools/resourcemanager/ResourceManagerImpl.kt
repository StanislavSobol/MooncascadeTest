package com.example.mooncascadetest.tools.resourcemanager

import android.content.Context
import androidx.annotation.ArrayRes
import androidx.annotation.StringRes

class ResourceManagerImpl(private val appContext: Context) : ResourceManager {

    override fun getString(@StringRes resId: Int) = appContext.getString(resId)

    override fun getString(@StringRes resId: Int, subStr: String) = appContext.getString(resId, subStr)

    override fun getStringArray(@ArrayRes resId: Int): Array<out String> = appContext.resources.getStringArray(resId)
}