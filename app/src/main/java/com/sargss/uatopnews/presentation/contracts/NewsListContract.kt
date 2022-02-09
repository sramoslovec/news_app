package com.sargss.uatopnews.presentation.contracts

import com.sargss.uatopnews.domain.models.NewsEntity

interface NewsListContract {

    abstract class Presenter : BaseContract.Presenter<View>() {
        abstract fun loadNews()
        abstract fun processSearch(query: String)
    }

    interface View : BaseContract.View {
        fun showNewsList(list: List<NewsEntity>)
        fun showNetworkError()
    }
}