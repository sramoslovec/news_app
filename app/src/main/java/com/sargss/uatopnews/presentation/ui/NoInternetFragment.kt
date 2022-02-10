package com.sargss.uatopnews.presentation.ui

import android.os.Bundle
import android.view.View
import com.sargss.uatopnews.databinding.FragmentNoInternetBinding

class NoInternetFragment : BaseFragment<FragmentNoInternetBinding>(FragmentNoInternetBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnRetry.setOnClickListener {
            (requireActivity() as MainActivity).showNewsListScreen()
        }
    }
}