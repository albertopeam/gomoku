package com.github.albertopeam.gomoku.domain

class MockBoard: Board, BoardState {
    override val columns: Int = 19
    override val rows: Int = 19
    val lastColumn: Int = columns - 1
    val lastRow: Int = rows - 1
    private var board: MutableMap<Position, Player> = mutableMapOf()

    override fun place(position: Position, player: Player) {
        board[position] = player
    }

    override fun get(position: Position): Player {
        return board[position] ?: Player.EMPTY
    }
}