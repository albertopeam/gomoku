package com.github.albertopeam.gomoku.domain

class Board {
    val columns = 19
    val rows = 19
    val lastColumn: Int = columns - 1
    val lastRow: Int = rows - 1
    private var board: MutableMap<Position, Player> = mutableMapOf()

    fun stones(): Int {
        return board.count()
    }

    /**
     * place the player piece into the position
     * @throws OutOfBoardException if the position is not inside the board
     * @throws SpaceOccupiedException if the position is already taken by a player
     */
    fun place(position: Position, player: Player) {
        place(position.row, position.column, player)
    }

    private fun place(row: Int, column: Int, player: Player) {
        val position = Position(row, column)
        checkBoard(position)
        checkFree(position)
        board[position] = player
    }

    /**
     * gets the player for a position, if not occupied then it will return empty
     * @throws OutOfBoardException if the position is not inside the board
     */
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