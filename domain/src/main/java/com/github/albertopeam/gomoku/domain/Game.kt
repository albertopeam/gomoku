package com.github.albertopeam.gomoku.domain

interface Game {
    fun takeTurn(position: Position)
    fun whoseTurn(): Player
    fun haveWinner(player: Player): Boolean
    fun board(): BoardState
}