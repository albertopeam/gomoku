package com.github.albertopeam.gomoku.domain

class GomokuRules {
    private val winTimes = 5

    fun haveWinner(board: Board): Player {
        (0 until board.rows).forEach {
            val winner = haveWinnerForRow(it, board)
            if (winner != Player.EMPTY) {
                return winner
            }
        }
        (0 until board.columns).forEach {
            val winner = haveWinnerForColumn(it, board)
            if (winner != Player.EMPTY) {
                return winner
            }
        }
        (0 until board.rows).forEach { row ->
            (0 until board.columns).forEach { column ->
                val winner = haveWinnerForDiagonal(Position(row, column), board)
                if (winner != Player.EMPTY) {
                    return winner
                }
            }
        }
        return Player.EMPTY
    }

    private fun haveWinnerForRow(row: Int, board: Board): Player {
        var times = 0
        var timesPlayer = Player.EMPTY
        for (i in 0 until board.columns) {
            val player = board.get(Position(row, i))
            if (player == Player.EMPTY) {
                times = 0
                timesPlayer = Player.EMPTY
            } else if (player == timesPlayer) {
                times++
            } else {
                times = 1
                timesPlayer = player
            }
            if (times >= winTimes) {
                return timesPlayer
            }
        }
        return Player.EMPTY
    }

    private fun haveWinnerForColumn(column: Int, board: Board): Player {
        var times = 0
        var timesPlayer = Player.EMPTY
        for (i in 0 until board.rows) {
            val player = board.get(Position(i, column))
            if (player == Player.EMPTY) {
                times = 0
                timesPlayer = Player.EMPTY
            } else if (player == timesPlayer) {
                times++
            } else {
                times = 1
                timesPlayer = player
            }
            if (times >= winTimes) {
                return timesPlayer
            }
        }
        return Player.EMPTY
    }

    private fun haveWinnerForDiagonal(position: Position, board: Board): Player {
        return try {
            val player = board.get(position)
            //TODO: empty player shortcut
            val winner = (1 until 4)
                .map { board.get(Position(position.row + it, position.column + it)) }
                .all { it == player }
            if (winner) {
                player
            } else {
                Player.EMPTY
            }
        } catch (e: OutOfBoardException) {
            Player.EMPTY
        }
    }
}