package com.sargss.uatopnews.screens.news

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sargss.uatopnews.App
import com.sargss.uatopnews.R
import com.sargss.uatopnews.contracts.NewsListContract
import com.sargss.uatopnews.data.ArticlesResponse
import com.sargss.uatopnews.databinding.FragmentNewsBinding
import javax.inject.Inject


class NewsFragment : Fragment(), NewsListContract.View {

    @Inject
    lateinit var presenter: NewsPresenter

    private var adapter: NewsRecyclerAdapter? = null

    private var _binding: FragmentNewsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
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
    }

    fun openInBrowser(link: String?) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            val chooser: Intent = Intent.createChooser(intent, getString(R.string.msg_choose_browser))

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
        val carsRecyclerView: RecyclerView = binding.newsList
        carsRecyclerView.layoutManager = LinearLayoutManager(context)

        adapter = NewsRecyclerAdapter(list, this)

        carsRecyclerView.adapter = adapter
        carsRecyclerView.addItemDecoration(
            ItemsMarginDecoration(
                requireContext(),
                resources.getDimension(R.dimen.news_card_margins)
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroy()
        _binding = null
    }
}