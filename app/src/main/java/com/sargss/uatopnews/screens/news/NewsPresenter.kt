package com.sargss.uatopnews.screens.news

import com.sargss.uatopnews.api.News
import com.sargss.uatopnews.contracts.NewsListContract
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsPresenter @Inject constructor() : NewsListContract.Presenter() {

    @Inject
    lateinit var news: News

    override fun loadNews() {
        CoroutineScope(Dispatchers.Default).launch {
            val list = news.getNews().articles

            launch(Dispatchers.Main) {
                getView().showNewsList(list)
            }
        }
    }
}