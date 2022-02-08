package com.sargss.uatopnews.di.components

import android.content.Context
import com.sargss.uatopnews.di.module.ApiModule
import com.sargss.uatopnews.di.module.AppModule
import com.sargss.uatopnews.presentation.ui.NewsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ApiModule::class])
interface AppComponent {

    fun inject(newsFragment: NewsFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}