package com.sargss.uatopnews.presentation.presenters

import com.sargss.uatopnews.data.api.News
import com.sargss.uatopnews.domain.mappers.NewsEntityMapper
import com.sargss.uatopnews.presentation.contracts.NewsListContract
import kotlinx.coroutines.*
import kotlinx.coroutines.Job
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
                    getView().showNewsList(
                        NewsEntityMapper().mapList(response.articles)
                    )
                }
            } catch (e: Exception) {
                when (e) {
                    is UnknownHostException -> getView().showNetworkError()
                    is ConnectException -> getView().showNetworkError()
                }
            }
        }
    }

    override fun processSearch(query: String) {
        job = CoroutineScope(Dispatchers.Default).launch {

            try {
                val response = news.getNewsByQuery(query)

                launch(Dispatchers.Main) {
                    getView().showNewsList(
                        NewsEntityMapper().mapList(response.articles)
                    )
                }
            } catch (e: Exception) {
                when (e) {
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