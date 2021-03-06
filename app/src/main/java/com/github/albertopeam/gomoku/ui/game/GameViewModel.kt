package com.github.albertopeam.gomoku.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.albertopeam.gomoku.domain.*

class GameViewModel(private val game: Game): ViewModel() {
    private val _playerTurn = MutableLiveData("")
    val playerTurn: LiveData<String> = _playerTurn
    private val _winStatus = MutableLiveData("")
    val winStatus: LiveData<String> = _winStatus
    val board: BoardState = game.board()

    init {
        haveWinnerOrWhoseTurn(game.whoseTurn())
    }

    val tap: (Int, Int) -> Unit = { row, column ->
        try {
            val player: Player = game.whoseTurn()
            game.takeTurn(Position(row, column))
            haveWinnerOrWhoseTurn(player)
        } catch(e: Exception) {}
    }

    private fun haveWinnerOrWhoseTurn(player: Player) {
        if (game.haveWinner(player)) {
            when (player) {
                Player.BLACK -> {
                    _winStatus.postValue("Black wins")
                }
                Player.WHITE -> {
                    _winStatus.postValue("White wins")
                }
                else -> {}
            }
        } else {
            when (game.whoseTurn()) {
                Player.BLACK -> {
                    _playerTurn.postValue("Black's turn")
                }
                Player.WHITE -> {
                    _playerTurn.postValue("White's turn")
                }
                else -> {}
            }
        }
    }
}