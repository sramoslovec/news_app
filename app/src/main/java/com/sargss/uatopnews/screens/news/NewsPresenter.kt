package com.sargss.uatopnews.screens.news

import android.util.Log
import com.sargss.uatopnews.api.News
import com.sargss.uatopnews.contracts.NewsListContract
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

class NewsPresenter @Inject constructor() : NewsListContract.Presenter() {

    @Inject
    lateinit var news: News

    private var job: Job? = null

    override fun loadNews() {
        job = CoroutineScope(Dispatchers.Default).launch {

            try {
                val response = news.getNews()

                launch(Dispatchers.Main) {
                    getView().showNewsList(response.articles)
                }
            } catch (t: Throwable) {
                when (t) {
                    is UnknownHostException -> getView().showNetworkError()
                    is ConnectException -> getView().showNetworkError()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }
}