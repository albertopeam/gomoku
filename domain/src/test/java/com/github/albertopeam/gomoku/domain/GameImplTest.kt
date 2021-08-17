package com.github.albertopeam.gomoku.domain

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual.equalTo
import org.junit.Before
import org.junit.Test

class GameImplTest {

    private lateinit var board: MockBoard
    private lateinit var rules: GomokuRules
    private lateinit var sut: GameImpl

    @Before
    fun setUp() {
        board = MockBoard()
        val boardState: BoardState = board
        rules = GomokuRules()
        sut = GameImpl(board, boardState, rules)
    }

    // takeTurn & whoseTurn

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

    @Test
    fun `given board with winner when ask turn then is empty`() {
        (0..4).forEach { board.place(Position(it, 0), Player.BLACK) }

        assertThat(sut.haveWinner(Player.BLACK), equalTo(true))
        assertThat(sut.whoseTurn(), equalTo(Player.EMPTY))
    }

    // haveWinner

    @Test
    fun `given board with winner when ask have winner then return true`() {
        (0..4).forEach { board.place(Position(it, 0), Player.BLACK) }

        assertThat(sut.haveWinner(Player.WHITE), equalTo(false))
        assertThat(sut.haveWinner(Player.BLACK), equalTo(true))
        assertThat(sut.whoseTurn(), equalTo(Player.EMPTY))
    }

    @Test
    fun `given board with no winner when ask have winner then return false`() {
        assertThat(sut.haveWinner(Player.WHITE), equalTo(false))
        assertThat(sut.haveWinner(Player.BLACK), equalTo(false))
        assertThat(sut.whoseTurn(), equalTo(Player.BLACK))
    }

    // after win

    @Test(expected = GameFinishedException::class)
    fun `given board with winner when place then throw game finished`() {
        (0..4).forEach { board.place(Position(it, 0), Player.BLACK) }

        assertThat(sut.haveWinner(Player.BLACK), equalTo(true))
        sut.takeTurn(Position(5, 0))
    }
}