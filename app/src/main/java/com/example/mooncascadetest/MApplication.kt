package com.example.mooncascadetest

import android.app.Application
import com.example.mooncascadetest.di.AppComponent
import com.example.mooncascadetest.di.DaggerAppComponent

class MApplication : Application() {
    private lateinit var daggerAppComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        daggerAppComponent = DaggerAppComponent
            .builder()
            .appContext(this.applicationContext)
            .build()
    }

    companion object {

        private lateinit var instance: MApplication

        fun getAppComponent(): AppComponent {
            return instance.daggerAppComponent
        }
    }
}