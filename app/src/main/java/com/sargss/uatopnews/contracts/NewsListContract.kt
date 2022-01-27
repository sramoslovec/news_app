package com.sargss.uatopnews.contracts

import com.sargss.uatopnews.data.ArticlesResponse

interface NewsListContract {

    abstract class Presenter : BaseContract.Presenter<View>() {
        abstract fun loadNews()
    }

    interface View : BaseContract.View {
        fun showNewsList(list: List<ArticlesResponse.Articles>)
    }
}