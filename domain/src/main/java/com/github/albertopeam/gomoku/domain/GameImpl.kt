package com.github.albertopeam.gomoku.domain

//TODO: block game with empty player to avoid doing moves after winning?
internal class GameImpl(private val board: BoardData, private val rules: GomokuRules): Game {
    private var player: Player = Player.BLACK

    override fun takeTurn(position: Position) {
        board.place(position, player)
        player = nextPlayer(player)
    }

    override fun whoseTurn(): Player {
        return player
    }

    override fun haveWinner(player: Player): Boolean {
        return rules.haveWinner(board, player)
    }

    override fun board(): BoardState {
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