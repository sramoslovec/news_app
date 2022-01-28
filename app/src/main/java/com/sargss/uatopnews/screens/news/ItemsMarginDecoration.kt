package com.sargss.uatopnews.screens.news

import android.content.Context
import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.recyclerview.widget.RecyclerView
import android.util.TypedValue
import android.view.View

class ItemsMarginDecoration(context: Context, margins: Float) : ItemDecoration() {

    private val calculatedMargins: Int

    init {
        val metrics = context.resources.displayMetrics
        calculatedMargins = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            margins,
            metrics
        ).toInt()
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemPosition = parent.getChildAdapterPosition(view)
        if (itemPosition == RecyclerView.NO_POSITION) {
            return
        }

        if (itemPosition == 0) {
            outRect.top = calculatedMargins
        }

        outRect.left = calculatedMargins
        outRect.right = calculatedMargins
        outRect.bottom = calculatedMargins
    }
}