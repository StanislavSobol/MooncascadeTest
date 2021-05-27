package com.example.mooncascadetest.data.repo

import com.example.mooncascadetest.data.api.MoonCascadeApi
import com.example.mooncascadetest.data.db.MoonCascadeDatabase
import com.example.mooncascadetest.tools.onlinenfoprovider.OnlineInfoProvider
import com.example.mooncascadetest.tools.resourcemanager.ResourceManager

class RepositoryImpl(
    private val db: MoonCascadeDatabase,
    private val api: MoonCascadeApi,
    private val resourceManager: ResourceManager,
    private val onlineInfoProvider: OnlineInfoProvider
) : Repository {

}