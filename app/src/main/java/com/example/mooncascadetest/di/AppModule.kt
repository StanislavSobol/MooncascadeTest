package com.example.mooncascadetest.di

import android.content.Context
import androidx.room.Room
import com.example.mooncascadetest.BuildConfig
import com.example.mooncascadetest.data.api.MoonCascadeApi
import com.example.mooncascadetest.data.db.MoonCascadeDatabase
import com.example.mooncascadetest.data.repo.Repository
import com.example.mooncascadetest.data.repo.RepositoryImpl
import com.example.mooncascadetest.tools.onlinenfoprovider.OnlineInfoProvider
import com.example.mooncascadetest.tools.onlinenfoprovider.OnlineInfoProviderImpl
import com.example.mooncascadetest.tools.resourcemanager.ResourceManager
import com.example.mooncascadetest.tools.resourcemanager.ResourceManagerImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideDatabase(appContext: Context): MoonCascadeDatabase {
        return Room
            .databaseBuilder(appContext, MoonCascadeDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            //   .registerTypeAdapter(Date::class.java, TimeTypeAdapter())
            .create()
    }

    @Provides
    @Singleton
    fun provideApi(gson: Gson): MoonCascadeApi {
        val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Platform.get().log(message)
            }
        }).apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.BASIC
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(MoonCascadeApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(
        db: MoonCascadeDatabase,
        api: MoonCascadeApi,
        resourceManager: ResourceManager,
        onlineInfoProvider: OnlineInfoProvider
    ): Repository {
        return RepositoryImpl(db, api, resourceManager, onlineInfoProvider)
    }

    @Singleton
    @Provides
    fun provideResourceManager(appContext: Context): ResourceManager {
        return ResourceManagerImpl(appContext)
    }

    @Singleton
    @Provides
    fun provideOnlineInfoProvider(appContext: Context): OnlineInfoProvider {
        return OnlineInfoProviderImpl(appContext)
    }

    companion object {
        private const val BASE_URL = "https://weather.aw.ee/"
        private const val DB_NAME = "db"
    }
}