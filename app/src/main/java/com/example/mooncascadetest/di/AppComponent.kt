package com.example.mooncascadetest.di

import android.content.Context
import com.example.mooncascadetest.data.repo.Repository
import com.example.mooncascadetest.tools.resourcemanager.ResourceManager
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent {

    fun repository(): Repository

    fun resourceManager(): ResourceManager

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appContext(appContext: Context): Builder

        fun build(): AppComponent
    }
}