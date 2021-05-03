package com.github.albertopeam.gomoku.domain

class GomokuGameFactory {
    companion object {
        fun make(): Game {
            val board = BoardData()
            val rules = GomokuRules()
            return GameImpl(board, rules)
        }
    }
}