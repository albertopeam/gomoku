package com.github.albertopeam.gomoku.domain

import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class GameTest {

    private lateinit var board: Board
    private lateinit var rules: GomokuRules
    private lateinit var sut: Game

    @Before
    fun setUp() {
        board = Board()
        rules = GomokuRules()
        sut = Game(board, rules)
    }

    @Test
    fun `when create game then first turn is white`() {
        assertEquals(sut.whoseTurn(), Player.BLACK)
    }
}