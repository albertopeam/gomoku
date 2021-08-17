package com.github.albertopeam.gomoku.domain

class GomokuGameFactory {
    companion object {
        fun make(board: Board, boardState: BoardState): Game {
            val rules = GomokuRules()
            return GameImpl(board, boardState, rules)
        }
    }
}