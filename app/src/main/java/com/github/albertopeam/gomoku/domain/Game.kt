package com.github.albertopeam.gomoku.domain

class Game(private val board: Board, private val rules: GomokuRules) {
    fun whoseTurn(): Player {
        return Player.BLACK
    }
}