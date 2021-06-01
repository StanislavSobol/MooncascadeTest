package com.example.mooncascadetest.presentation.mainscreen

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.example.mooncascadetest.R
import com.example.mooncascadetest.tools.HOME_SITE_URL

class CustomTabsHelper(context: Context) {

    init {
        val builder = CustomTabsIntent.Builder()
            .setDefaultColorSchemeParams(
                CustomTabColorSchemeParams.Builder()
                    .setToolbarColor(ContextCompat.getColor(context, R.color.black))
                    .build()
            )
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(context, Uri.parse(HOME_SITE_URL))
    }
}