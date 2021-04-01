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
        assertThat(sut.haveWinner(board), equalTo(Player.EMPTY))
    }

    @Test
    fun `when board is not empty but not win then is not winner`() {
        board.place(Position(0, 0), Player.WHITE)
        assertThat(sut.haveWinner(board), equalTo(Player.EMPTY))
    }

    // horizontal

    @Test
    fun `when five in row in the first row for same player then is winner`() {
        (0..4).forEach { board.place(Position(0, it), Player.WHITE) }

        assertThat(sut.haveWinner(board), equalTo(Player.WHITE))
    }

    @Test
    fun `when five not consecutive in the first row for different players then is not winner`() {
        arrayOf(0,2,4,6,8).forEach { board.place(Position(0, it), Player.WHITE) }
        arrayOf(1,3,5,7,9).forEach { board.place(Position(0, it), Player.BLACK) }

        assertThat(sut.haveWinner(board), equalTo(Player.EMPTY))
    }

    @Test
    fun `when two in a row in the first row and three in a row in the second row then is not winner`() {
        (0..1).forEach { board.place(Position(0, it), Player.BLACK) }
        (0..2).forEach { board.place(Position(1, it), Player.BLACK) }

        assertThat(sut.haveWinner(board), equalTo(Player.EMPTY))
    }

    @Test
    fun `when four in row in the first row for same player then is not winner`() {
        (4..7).forEach { board.place(Position(0, it), Player.WHITE) }

        assertThat(sut.haveWinner(board), equalTo(Player.EMPTY))
    }

    @Test
    fun `when five in row but not consecutive in the first row for same player then is not winner`() {
        (0..3).forEach { board.place(Position(0, it), Player.WHITE) }
        board.place(Position(0, 4), Player.BLACK)
        board.place(Position(0, 5), Player.WHITE)

        assertThat(sut.haveWinner(board), equalTo(Player.EMPTY))
    }

    @Test
    fun `when five in row for same player with an empty in the middle then is not winner`() {
        (0..3).forEach { board.place(Position(0, it), Player.WHITE) }
        board.place(Position(0, 5), Player.WHITE)

        assertThat(sut.haveWinner(board), equalTo(Player.EMPTY))
    }

    @Test
    fun `when five in row in any row for same player then is winner`() {
        (0..board.rows).forEach {
            board = Board()
            (0..5).forEach { board.place(Position(0, it), Player.WHITE) }

            assertThat(sut.haveWinner(board), equalTo(Player.WHITE))
        }
    }

    @Test
    fun `when six in row in any row for same player then is winner`() {
        (0..6).forEach { board.place(Position(0, it), Player.WHITE) }

        assertThat(sut.haveWinner(board), equalTo(Player.WHITE))
    }

    // vertical

    @Test
    fun `when five in a col in the first column then is winner`() {
        (0..4).forEach { board.place(Position(it, 0), Player.BLACK) }

        assertThat(sut.haveWinner(board), equalTo(Player.BLACK))
    }

    @Test
    fun `when five in a col at the end of the first column then is winner`() {
        (board.rows-1 downTo board.rows-5).forEach { board.place(Position(it, board.columns-1), Player.BLACK) }

        assertThat(sut.haveWinner(board), equalTo(Player.BLACK))
    }

    @Test
    fun `when five in a col in the first column for white then is winner`() {
        (0..4).forEach { board.place(Position(it, 0), Player.WHITE) }

        assertThat(sut.haveWinner(board), equalTo(Player.WHITE))
    }

    @Test
    fun `when two in a col in the first column and three in a col in the second column then is not winner`() {
        (0..1).forEach { board.place(Position(it, 0), Player.BLACK) }
        (0..2).forEach { board.place(Position(it, 1), Player.BLACK) }

        assertThat(sut.haveWinner(board), equalTo(Player.EMPTY))
    }

    @Test
    fun `when five not consecutive in the first column for different players then is not winner`() {
        arrayOf(0,2,4,6,8).forEach { board.place(Position(it, 0), Player.WHITE) }
        arrayOf(1,3,5,7,9).forEach { board.place(Position(it, 0), Player.BLACK) }

        assertThat(sut.haveWinner(board), equalTo(Player.EMPTY))
    }

    @Test
    fun `when four in a column in the first column for same player then is not winner`() {
        (4..7).forEach { board.place(Position(it, 0), Player.WHITE) }

        assertThat(sut.haveWinner(board), equalTo(Player.EMPTY))
    }

    @Test
    fun `when four in a column in the last column for same player then is not winner`() {
        (board.rows-1 downTo board.rows-4).forEach { board.place(Position(it, board.columns-1), Player.BLACK) }

        assertThat(sut.haveWinner(board), equalTo(Player.EMPTY))
    }

    @Test
    fun `when five in column but not consecutive in the first column for same player then is not winner`() {
        (0..3).forEach { board.place(Position(it, 0), Player.WHITE) }
        board.place(Position(4, 0), Player.BLACK)
        board.place(Position(5, 0), Player.WHITE)

        assertThat(sut.haveWinner(board), equalTo(Player.EMPTY))
    }

    @Test
    fun `when five in column for same player with an empty in the middle then is not winner`() {
        (0..3).forEach { board.place(Position(it, 0), Player.WHITE) }
        board.place(Position(5, 0), Player.WHITE)

        assertThat(sut.haveWinner(board), equalTo(Player.EMPTY))
    }

    @Test
    fun `when five in column in any row for same player then is winner`() {
        (0..board.rows).forEach {
            board = Board()
            (0..5).forEach { board.place(Position(it, 0), Player.WHITE) }

            assertThat(sut.haveWinner(board), equalTo(Player.WHITE))
        }
    }

    @Test
    fun `when six in column in any row for same player then is winner`() {
        (0..6).forEach { board.place(Position(it, 0), Player.WHITE) }

        assertThat(sut.haveWinner(board), equalTo(Player.WHITE))
    }
}