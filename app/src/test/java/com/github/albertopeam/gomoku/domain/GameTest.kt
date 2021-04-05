package com.github.albertopeam.gomoku.domain

import org.hamcrest.core.IsEqual.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

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
    fun `when create game then first turn is black`() {
        assertThat(sut.whoseTurn(), equalTo(Player.BLACK))
    }

    @Test
    fun `given first turn done when ask turn then is white`() {
        sut.takeTurn(Position(0, 0))

        assertThat(board.get(Position(0,0)), equalTo(Player.BLACK))
        assertThat(sut.whoseTurn(), equalTo(Player.WHITE))
    }

    @Test
    fun `given second turn done when ask turn then is black`() {
        sut.takeTurn(Position(0, 0))
        sut.takeTurn(Position(0, 1))

        assertThat(board.get(Position(0,0)), equalTo(Player.BLACK))
        assertThat(board.get(Position(0,1)), equalTo(Player.WHITE))
        assertThat(sut.whoseTurn(), equalTo(Player.BLACK))
    }

}