package com.sargss.uatopnews.presentation.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.parseAsHtml
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sargss.uatopnews.data.api.ArticlesResponse
import com.sargss.uatopnews.databinding.CardNewsItemBinding
import com.sargss.uatopnews.presentation.ui.NewsFragment
import java.text.SimpleDateFormat
import java.util.*

class NewsRecyclerAdapter(private val fragment: NewsFragment) :
    RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder>() {

    private var list: List<ArticlesResponse.Articles> = listOf()
        set(value) {
            val diffCallback = DiffUtilCallback(list, value)
            val diffResult = DiffUtil.calculateDiff(diffCallback)

            cachedList = field
            field = value

            diffResult.dispatchUpdatesTo(this)
        }


    private var cachedList: List<ArticlesResponse.Articles> = listOf()

    fun setItems(newList: List<ArticlesResponse.Articles>) {
        list = newList
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = CardNewsItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(view)
    }

    class ViewHolder(binding: CardNewsItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val imageNews: ImageView = binding.newsImage
        val textTitle: TextView = binding.textNewsTitle
        val textPublishedTime: TextView = binding.textNewsTime
        val textSource: TextView = binding.textNewsSource
        val textDescription: TextView = binding.textNewsDescription
        val btnOpenNewsInBrowser: Button = binding.btnOpenInBrowser
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: ArticlesResponse.Articles = list[position]

        if (URLUtil.isValidUrl(model.urlToImage)) {
            holder.imageNews.visibility = View.VISIBLE
            Glide.with(fragment)
                .load(model.urlToImage)
                .centerCrop()
                .into(holder.imageNews)
        }

        try {
            val date: Date? = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                .parse(model.publishedAt!!)

            holder.textPublishedTime.text = SimpleDateFormat("HH:mm").format(date!!)
        } catch (e: Exception) {
            holder.textPublishedTime.visibility = View.INVISIBLE
        }

        showTextOrHide(holder.textTitle, model.title)
        showTextOrHide(holder.textSource, model.source?.name)
        showTextOrHide(holder.textDescription, model.description)

        holder.btnOpenNewsInBrowser.setOnClickListener {
            fragment.openInBrowser(model.url)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private fun showTextOrHide(textElement: TextView, value: String?) {
        if (value.isNullOrBlank()) {
            textElement.visibility = View.GONE
        } else {
            // i saw at least apostrophe encoded in HTML code, so to correctly show encoded symbols
            // i just parse all text as html
            textElement.text = value.parseAsHtml()
        }
    }

    class DiffUtilCallback(
        private val oldList: List<ArticlesResponse.Articles>,
        private val newList: List<ArticlesResponse.Articles>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].url == newList[newItemPosition].url
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}