package com.sargss.uatopnews.di.module

import com.sargss.uatopnews.contracts.NewsListContract
import com.sargss.uatopnews.screens.news.NewsPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideNewsListPresenter(): NewsListContract.Presenter = NewsPresenter()
}