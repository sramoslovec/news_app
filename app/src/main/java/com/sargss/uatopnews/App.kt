package com.sargss.uatopnews

import android.app.Application
import com.sargss.uatopnews.di.components.AppComponent
import com.sargss.uatopnews.di.components.DaggerAppComponent

class App : Application() {

    var appComponent: AppComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .factory()
            .create(applicationContext)
    }
}