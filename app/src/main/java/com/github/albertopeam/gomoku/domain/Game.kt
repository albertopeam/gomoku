package com.github.albertopeam.gomoku.domain

class Game(private val board: Board, private val rules: GomokuRules) {
    var player: Player = Player.BLACK

    fun takeTurn(position: Position) {
        board.place(position, player)
        player = nextPlayer(player)
    }

    fun whoseTurn(): Player {
        return player
    }

    private fun nextPlayer(player: Player): Player {
        return if (player == Player.BLACK) {
            Player.WHITE
        } else {
            Player.BLACK
        }
    }
}