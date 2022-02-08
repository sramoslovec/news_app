package com.sargss.uatopnews.presentation.ui

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sargss.uatopnews.core.App
import com.sargss.uatopnews.R
import com.sargss.uatopnews.presentation.contracts.NewsListContract
import com.sargss.uatopnews.data.models.ArticlesResponse
import com.sargss.uatopnews.databinding.FragmentNewsBinding
import com.sargss.uatopnews.presentation.ui.adapter.ItemsMarginDecoration
import com.sargss.uatopnews.presentation.presenters.NewsPresenter
import com.sargss.uatopnews.presentation.ui.adapter.NewsRecyclerAdapter
import javax.inject.Inject


class NewsFragment : Fragment(), NewsListContract.View {

    @Inject
    lateinit var presenter: NewsPresenter

    private var adapter: NewsRecyclerAdapter? = null

    private var _binding: FragmentNewsBinding? = null

    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as App).appComponent?.inject(this)

        presenter.attachView(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.loadNews()

        binding.inputNewsSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean = true

            override fun onQueryTextChange(newText: String): Boolean {
                presenter.processSearch(newText)
                return true
            }
        })
    }

    fun openInBrowser(link: String?) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            val chooser: Intent =
                Intent.createChooser(intent, getString(R.string.msg_choose_browser))

            startActivity(chooser)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                requireContext(),
                getString(R.string.msg_open_news_browser_error),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun showNewsList(list: List<ArticlesResponse.Articles>) {
        if (adapter == null) {
            createNewsList()
        }

        adapter!!.setItems(list)
    }

    private fun createNewsList() {
        val carsRecyclerView: RecyclerView = binding.newsList

        carsRecyclerView.layoutManager = LinearLayoutManager(context)

        adapter = NewsRecyclerAdapter(this)

        carsRecyclerView.adapter = adapter
        carsRecyclerView.addItemDecoration(
            ItemsMarginDecoration(
                resources.displayMetrics,
                resources.getDimension(R.dimen.news_card_margins)
            )
        )
    }

    override fun showNetworkError() {
        (requireActivity() as MainActivity).showConnectionErrorScreen()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroy()
        _binding = null
    }
}