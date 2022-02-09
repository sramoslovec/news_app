package com.sargss.uatopnews.domain.models

data class NewsEntity(
    val title: String,
    val source: String,
    val description: String,
    val time: String,
    val url: String,
    val imageUrl: String
)