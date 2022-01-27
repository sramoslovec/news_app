package com.sargss.uatopnews.screens.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sargss.uatopnews.R
import com.sargss.uatopnews.data.ArticlesResponse

class NewsRecyclerAdapter(
    private val list: List<ArticlesResponse.Articles>
) :
    RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.card_news_item, parent, false)

        return ViewHolder(view)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textTitle: TextView = view.findViewById(R.id.text_news_title)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: ArticlesResponse.Articles = list[position]

        holder.textTitle.text = model.title

        /*holder.itemView.setOnClickListener {
        }*/
    }

    override fun getItemCount(): Int {
        return list.size
    }
}