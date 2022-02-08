package com.sargss.uatopnews.presentation.contracts

import com.sargss.uatopnews.data.models.ArticlesResponse

interface NewsListContract {

    abstract class Presenter : BaseContract.Presenter<View>() {
        abstract fun loadNews()
        abstract fun processSearch(query: String)
    }

    interface View : BaseContract.View {
        fun showNewsList(list: List<ArticlesResponse.Articles>)
        fun showNetworkError()
    }
}