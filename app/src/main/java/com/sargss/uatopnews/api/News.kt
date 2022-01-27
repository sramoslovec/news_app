package com.sargss.uatopnews.api

import com.sargss.uatopnews.data.ArticlesResponse
import retrofit2.http.GET

interface News {

    @GET("/v2/top-headlines?country=ua&apiKey=f9161c28206343ddafce58e79dc03bb4")
    suspend fun getNews(): ArticlesResponse

    companion object {
        const val BASE_URL = "https://newsapi.org/"
    }
}