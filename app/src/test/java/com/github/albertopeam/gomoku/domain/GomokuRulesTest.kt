package com.github.albertopeam.gomoku.domain

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class GomokuRulesTest {

    private lateinit var board: Board
    private lateinit var sut: GomokuRules

    @Before
    fun setUp() {
        board = Board()
        sut = GomokuRules()
    }

    @Test
    fun `when board is empty then is not winner`() {
        assertThat(Player.EMPTY, equalTo(sut.haveWinner(board)))
    }

    @Test
    fun `when board is not empty but not win then is not winner`() {
        board.place(Position(0, 0), Player.WHITE)
        assertThat(Player.EMPTY, equalTo(sut.haveWinner(board)))
    }

    @Test
    fun `when five in row in the first row for same player then is winner`() {
        (0..4).forEach { board.place(Position(0, it), Player.WHITE) }

        assertThat(Player.WHITE, equalTo(sut.haveWinner(board)))
    }
}