package com.sargss.uatopnews.data.api

import com.sargss.uatopnews.data.models.ArticlesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface News {

    @GET("/v2/top-headlines?country=ua")
    suspend fun getNews(): ArticlesResponse

    @GET("/v2/top-headlines?country=ua")
    suspend fun getNewsByQuery(@Query("q") q: String): ArticlesResponse

    companion object {
        const val BASE_URL = "https://newsapi.org/"
        const val API_KEY = "f9161c28206343ddafce58e79dc03bb4"
    }
}