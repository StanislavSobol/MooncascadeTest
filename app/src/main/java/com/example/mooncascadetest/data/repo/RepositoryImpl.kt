package com.example.mooncascadetest.data.repo

import com.example.mooncascadetest.R
import com.example.mooncascadetest.data.api.MoonCascadeApi
import com.example.mooncascadetest.data.db.MoonCascadeDatabase
import com.example.mooncascadetest.tools.MoonCascadeException
import com.example.mooncascadetest.tools.onlinenfoprovider.OnlineInfoProvider
import com.example.mooncascadetest.tools.resourcemanager.ResourceManager

class RepositoryImpl(
    private val db: MoonCascadeDatabase,
    private val api: MoonCascadeApi,
    private val resourceManager: ResourceManager,
    private val onlineInfoProvider: OnlineInfoProvider
) : Repository {


    override suspend fun requestForecastAndPlaceToDb() {
        if (!onlineInfoProvider.isOnline) {
            throw MoonCascadeException(resourceManager.getString(R.string.offline))
        }

        val response = api.getLastData()
        val response2 = api.getLastData()


        // db.getVehiclesDao().deleteAll()
//        val response = api.getLastData()
//            response.response.forEach { vehiclesDb.getVehiclesDao().insert(VehicleEntity.from(it)) }
//        } else {
//            throw FleetCompleteBadStatusException("Wrong status when requesting last vehicles. Status ${response.status}")
//        }
    }

}