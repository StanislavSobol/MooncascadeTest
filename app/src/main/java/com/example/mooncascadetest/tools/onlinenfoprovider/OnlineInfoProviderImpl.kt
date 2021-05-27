package com.example.mooncascadetest.tools.onlinenfoprovider

import android.content.Context
import android.net.ConnectivityManager

class OnlineInfoProviderImpl(private val appContext: Context) : OnlineInfoProvider {

    override val isOnline: Boolean
        get() = isOnlineFromServices()

    // TODO Solve the deprecations
    private fun isOnlineFromServices(): Boolean {
        val cm = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }
}