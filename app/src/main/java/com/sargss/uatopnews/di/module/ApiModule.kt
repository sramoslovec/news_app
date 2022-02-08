package com.sargss.uatopnews.di.module

import com.sargss.uatopnews.data.api.NetworkInterceptor
import com.sargss.uatopnews.data.api.News
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    @Singleton
    @Provides
    fun newsClient(
        retrofitBuilder: Retrofit.Builder
    ): News {
        return retrofitBuilder.baseUrl(News.BASE_URL)
            .build()
            .create(News::class.java)
    }

    @Singleton
    @Provides
    fun baseRetrofitBuilder(
        httpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .client(httpClient)
            .addConverterFactory(gsonConverterFactory)
    }

    @Singleton
    @Provides
    fun httpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    fun gsonFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun interceptor(): Interceptor = NetworkInterceptor()
}