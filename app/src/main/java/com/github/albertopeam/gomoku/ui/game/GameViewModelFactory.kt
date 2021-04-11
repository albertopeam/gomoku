package com.github.albertopeam.gomoku.ui.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.albertopeam.gomoku.domain.Game

class GameViewModelFactory(private val game: Game): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GameViewModel(game) as T
    }
}