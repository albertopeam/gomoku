package com.github.albertopeam.gomoku.domain

interface BoardState {
    /**
     * Number of columns that the board has, from 0..<columns-1
     */
    val columns: Int
    /**
     * Number of rows that the board has, from 0..<rows-1
     */
    val rows: Int
    /**
     * gets the player for a position, if not occupied then it will return empty
     * @throws OutOfBoardException if the position is not inside the board
     */
    fun get(position: Position): Player
}