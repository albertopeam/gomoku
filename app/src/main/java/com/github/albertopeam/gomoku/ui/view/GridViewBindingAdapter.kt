package com.github.albertopeam.gomoku.ui.view

import android.view.MotionEvent
import androidx.databinding.BindingAdapter
import com.github.albertopeam.gomoku.domain.BoardState

@BindingAdapter("onTapListener")
fun GridView.onTapListener(lambda: ((row: Int, column: Int) -> Unit)?) {
    setOnTouchListener { view, event ->
        if (event.actionMasked == MotionEvent.ACTION_UP) {
            val position = position(event)
            lambda?.invoke(position.first, position.second)
            invalidate()
            view.performClick()
        }
        true
    }
}

@BindingAdapter("boardState")
fun GridView.setBoardState(boardState: BoardState) {
    this.board = boardState
}