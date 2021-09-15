package com.github.albertopeam.gomoku.ui.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.albertopeam.gomoku.data.GomokuBoardFactory
import com.github.albertopeam.gomoku.domain.GomokuGameFactory

class GameViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val board = GomokuBoardFactory.make()
        val game = GomokuGameFactory.make(board, board)
        return GameViewModel(game) as T
    }
}