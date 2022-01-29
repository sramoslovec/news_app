package com.sargss.uatopnews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.sargss.uatopnews.screens.news.NewsFragment
import com.sargss.uatopnews.screens.offline.NoInternetFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showNewsListScreen()
    }

    fun showNewsListScreen() {
        supportFragmentManager.commit {
            replace(R.id.fragment_container_view, NewsFragment())
            addToBackStack(null)
        }
    }

    fun showConnectionErrorScreen() {
        supportFragmentManager.commit {
            replace(R.id.fragment_container_view, NoInternetFragment())
            addToBackStack(null)
        }
    }
}