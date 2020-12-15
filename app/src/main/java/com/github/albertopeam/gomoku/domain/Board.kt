package com.github.albertopeam.gomoku.domain

import java.lang.Exception

class Board {
    val size = 19
    private var board: MutableMap<Position, Player> = mutableMapOf()

    fun stones(): Int {
        return board.count()
    }

    fun place(row: Int, column: Int, player: Player) {
        val position = Position(row, column)
        checkBoard(position)
        checkFree(position)
        board[position] = player
    }

    fun get(row: Int, column: Int): Player {
        val position = Position(row, column)
        checkBoard(position)
        val player = board[position]
        return player ?: Player.EMPTY
    }

    private fun checkBoard(position: Position) {
        if (position.row < 0 || position.row >= size || position.column < 0 || position.column >= size) {
            throw OutOfBoardException()
        }
    }

    private fun checkFree(position: Position) {
        if (get(position.row, position.column) != Player.EMPTY) {
            throw SpaceOccupiedException()
        }
    }
}

private data class Position(val row: Int, val column: Int)

enum class Player {
    WHITE, BLACK, EMPTY
}

class SpaceOccupiedException: Exception()
class OutOfBoardException: Exception()