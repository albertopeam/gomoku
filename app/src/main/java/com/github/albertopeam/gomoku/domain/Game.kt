package com.github.albertopeam.gomoku.domain

//TODO: block game with empty player to avoid doing moves after winning?
class Game(private val board: BoardData, private val rules: GomokuRules) {
    private var player: Player = Player.BLACK

    fun takeTurn(position: Position) {
        board.place(position, player)
        player = nextPlayer(player)
    }

    fun whoseTurn(): Player {
        return player
    }

    fun haveWinner(player: Player): Boolean {
        return rules.haveWinner(board, player)
    }

    fun board(): BoardState {
        return board
    }

    private fun nextPlayer(player: Player): Player {
        return if (player == Player.BLACK) {
            Player.WHITE
        } else {
            Player.BLACK
        }
    }
}