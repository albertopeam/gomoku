package com.github.albertopeam.gomoku.domain

interface Board {
    /**
     * Number of columns that the board has, from 0..<columns-1
     */
    val columns: Int
    /**
     * Number of rows that the board has, from 0..<rows-1
     */
    val rows: Int
    /**
     * place the player piece into the position
     * @throws OutOfBoardException if the position is not inside the board
     * @throws SpaceOccupiedException if the position is already taken by a player
     */
    fun place(position: Position, player: Player)
}