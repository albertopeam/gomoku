package com.github.albertopeam.gomoku.domain

//TODO: still some public dependencies: BoardData, Game, GomokuRules
class GomokuGameFactory {
    companion object {
        fun make(): Game {
            val board = BoardData()
            val rules = GomokuRules()
            return Game(board, rules)
        }
    }
}