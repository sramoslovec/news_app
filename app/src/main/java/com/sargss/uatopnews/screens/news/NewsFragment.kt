package com.sargss.uatopnews.screens.news

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sargss.uatopnews.R
import com.sargss.uatopnews.contracts.NewsListContract
import com.sargss.uatopnews.data.ArticlesResponse

class NewsFragment : Fragment(), NewsListContract.View {

    private var adapter: NewsRecyclerAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }


    override fun showNewsList(list: List<ArticlesResponse.Articles>) {
        val carsRecyclerView: RecyclerView = requireView().findViewById(R.id.news_list)
        carsRecyclerView.layoutManager = LinearLayoutManager(context)

        adapter = NewsRecyclerAdapter(list)
        carsRecyclerView.adapter = adapter
    }
}