package com.github.albertopeam.gomoku.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.albertopeam.gomoku.domain.Game
import com.github.albertopeam.gomoku.domain.Player
import com.github.albertopeam.gomoku.domain.Position

class GameViewModel(private val game: Game): ViewModel() {
    private val _playerTurn = MutableLiveData("Black's turn")
    val playerTurn: LiveData<String> = _playerTurn

    val tap: (Int, Int) -> Unit = { row, column ->
        game.takeTurn(Position(row, column))

        when (game.player) {
            Player.BLACK -> {
                _playerTurn.postValue("Black's turn")
            }
            Player.WHITE -> {
                _playerTurn.postValue("White's turn")
            }
            else -> {
                _playerTurn.postValue("???")
            }
        }
    }
}