package com.sargss.uatopnews.domain.mappers

import android.annotation.SuppressLint
import androidx.core.text.parseAsHtml
import com.sargss.uatopnews.data.models.ArticlesResponse
import com.sargss.uatopnews.domain.models.NewsEntity
import java.text.SimpleDateFormat
import java.util.*

class NewsEntityMapper {

    fun mapList(list: List<ArticlesResponse.Articles>): List<NewsEntity> {
        val newList: MutableList<NewsEntity> = mutableListOf()

        list.forEach {
            newList.add(map(it))
        }

        return newList.toList()
    }

    @SuppressLint("SimpleDateFormat")
    fun map(responseModel: ArticlesResponse.Articles): NewsEntity {

        // i saw at least apostrophe html encoded, so to correctly show encoded symbols parseAsHtml
        val description = responseModel.description?.parseAsHtml().toString()

        var publishedTime = ""

        try {
            val date: Date? = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                .parse(responseModel.publishedAt!!)

            publishedTime = SimpleDateFormat("HH:mm").format(date!!)
        } catch (e: Exception) {
            // on error related to datetime let formatted time string be empty
        }

        return NewsEntity(
            title = responseModel.title.toString(),
            source = responseModel.source?.name.toString(),
            description = description,
            time = publishedTime,
            url = responseModel.url.toString(),
            imageUrl = responseModel.urlToImage.toString()
        )
    }
}