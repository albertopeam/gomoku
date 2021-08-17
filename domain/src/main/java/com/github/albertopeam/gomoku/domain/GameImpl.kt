package com.github.albertopeam.gomoku.domain

internal class GameImpl(private val board: Board, private val boardState: BoardState, private val rules: GomokuRules): Game {
    private var player: Player = Player.BLACK

    override fun takeTurn(position: Position) {
        if (player == Player.EMPTY) {
            throw GameFinishedException()
        }
        board.place(position, player)
        player = nextPlayer(player)
    }

    override fun whoseTurn(): Player {
        return player
    }

    override fun haveWinner(player: Player): Boolean {
        val winner = rules.haveWinner(boardState, player)
        if (winner) {
            this.player = Player.EMPTY
        }
        return winner
    }

    override fun board(): BoardState {
        return boardState
    }

    private fun nextPlayer(player: Player): Player {
        return if (player == Player.BLACK) {
            Player.WHITE
        } else {
            Player.BLACK
        }
    }
}