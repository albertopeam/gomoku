package com.github.albertopeam.gomoku.ui.view

import android.view.MotionEvent
import androidx.databinding.BindingAdapter
import com.github.albertopeam.gomoku.domain.Player
import com.github.albertopeam.gomoku.domain.Position

@BindingAdapter("onTapListener")
fun GridView.onTapListener(lambda: ((row: Int, column: Int) -> Unit)?) {
    setOnTouchListener { view, event ->
        if (event.actionMasked == MotionEvent.ACTION_UP) {
            val position = position(event)
            board?.place(Position(position.first, position.second), Player.WHITE) //TODO: move to viewModel
            lambda?.invoke(position.first, position.second)
            invalidate()
            view.performClick()
        }
        true
    }
}