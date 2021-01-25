package com.github.albertopeam.gomoku.domain

import java.lang.Exception

class Board {
    val columns = 19
    val rows = 19
    private var board: MutableMap<Position, Player> = mutableMapOf()

    fun stones(): Int {
        return board.count()
    }

    fun place(position: Position, player: Player) {
        place(position.row, position.column, player)
    }

    private fun place(row: Int, column: Int, player: Player) {
        val position = Position(row, column)
        checkBoard(position)
        checkFree(position)
        board[position] = player
    }

    fun get(position: Position): Player {
        return get(position.row, position.column)
    }

   private fun get(row: Int, column: Int): Player {
        val position = Position(row, column)
        checkBoard(position)
        val player = board[position]
        return player ?: Player.EMPTY
    }

    private fun checkBoard(position: Position) {
        if (position.row < 0 || position.row >= rows || position.column < 0 || position.column >= columns) {
            throw OutOfBoardException()
        }
    }

    private fun checkFree(position: Position) {
        if (get(position.row, position.column) != Player.EMPTY) {
            throw SpaceOccupiedException()
        }
    }
}

data class Position(val row: Int, val column: Int)

enum class Player {
    WHITE, BLACK, EMPTY
}

class SpaceOccupiedException: Exception()
class OutOfBoardException: Exception()