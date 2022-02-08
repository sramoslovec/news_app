package com.sargss.uatopnews.presentation.ui.adapter

import android.graphics.Rect
import android.util.DisplayMetrics
import androidx.recyclerview.widget.RecyclerView
import android.util.TypedValue
import android.view.View

class ItemsMarginDecoration(
    metrics: DisplayMetrics,
    margins: Float
) : RecyclerView.ItemDecoration() {

    private val calculatedMargins: Int = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        margins,
        metrics
    ).toInt()

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