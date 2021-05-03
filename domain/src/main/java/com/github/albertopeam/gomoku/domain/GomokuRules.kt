package com.github.albertopeam.gomoku.domain

//TODO: need algorithm improvements, three double for loops, first one can be introduced in third one first level.
internal class GomokuRules {
    private val winTimes = 5

    fun haveWinner(board: BoardData, player: Player): Boolean {
        (0 until board.rows).forEach {
            val hasRowWinner = haveWinnerForRow(it, board, player)
            if (hasRowWinner) {
                return true
            }
        }
        (0 until board.columns).forEach {
            val hasColumnWinner = haveWinnerForColumn(it, board, player)
            if (hasColumnWinner) {
                return true
            }
        }
        (0 until board.rows).forEach { row ->
            (0 until board.columns).forEach { column ->
                val position = Position(row, column)
                val hasTopBottomWinner = haveWinnerForTopBottomDiagonal(position, board, player)
                val hasBottomTopWinner = haveWinnerForBottomTopDiagonal(position, board, player)
                if (hasTopBottomWinner) {
                    return true
                }
                if (hasBottomTopWinner) {
                    return true
                }
            }
        }
        return false
    }

    private fun haveWinnerForRow(row: Int, board: BoardData, player: Player): Boolean {
        var times = 0
        for (i in 0 until board.columns) {
            val playerForPosition = board.get(Position(row, i))
            if (playerForPosition != player) {
                times = 0
            } else if (playerForPosition == player) {
                times++
            }
            if (times >= winTimes) {
                return true
            }
        }
        return false
    }

    private fun haveWinnerForColumn(column: Int, board: BoardData, player: Player): Boolean {
        var times = 0
        for (i in 0 until board.rows) {
            val playerForPosition = board.get(Position(i, column))
            if (playerForPosition != player) {
                times = 0
            } else if (playerForPosition == player) {
                times++
            }
            if (times >= winTimes) {
                return true
            }
        }
        return false
    }

    private fun haveWinnerForTopBottomDiagonal(position: Position, board: BoardData, player: Player): Boolean {
        return try {
            (0 until 5)
                .map { board.get(Position(position.row + it, position.column + it)) }
                .all { it == player }
        } catch (e: OutOfBoardException) {
            false
        }
    }

    private fun haveWinnerForBottomTopDiagonal(position: Position, board: BoardData, player: Player): Boolean {
        return try {
            (0 until 5)
                .map { board.get(Position(position.row - it, position.column + it)) }
                .all { it == player }
        } catch (e: OutOfBoardException) {
            false
        }
    }
}