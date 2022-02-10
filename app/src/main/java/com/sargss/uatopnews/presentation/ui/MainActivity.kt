package com.sargss.uatopnews.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.sargss.uatopnews.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            showNewsListScreen()
        }
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