package com.example.mooncascadetest.tools.resourcemanager

import android.content.Context

class ResourceManagerImpl(private val appContext: Context) : ResourceManager {

    override fun getString(resId: Int) = appContext.getString(resId)

    override fun getString(resId: Int, subStr: String) = appContext.getString(resId, subStr)
}