package com.github.albertopeam.gomoku.domain

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class BoardTest {

    private lateinit var sut: Board

    @Before
    fun setUp() {
        sut = Board()
    }

    @Test
    fun `when create board then number of stones is zero`() {
        assertEquals(0, sut.stones())
    }

    @Test
    fun `when place a stone in a position then gets added`() {
        sut.place(0, 0, Player.WHITE)

        assertEquals(1, sut.stones())
        assertEquals(Player.WHITE, sut.get(0, 0))
    }

    @Test
    fun `when place white stone in a position then player in this position is white`() {
        assertEquals(Player.EMPTY, sut.get(1, 1))

        sut.place(1, 1, Player.WHITE)

        assertEquals(Player.WHITE, sut.get(1, 1))
    }

    @Test(expected = SpaceOccupiedException::class)
    fun `when place a stone in a occuppied intersection position then throw`() {
        sut.place(1, 1, Player.WHITE)

        assertEquals(Player.WHITE, sut.get(1, 1))

        sut.place(1, 1, Player.BLACK)
    }

    @Test(expected = OutOfBoardException::class)
    fun `when place a stone on the left of the board then throw`() {
        sut.place(-1, 0, Player.WHITE)
    }

    @Test(expected = OutOfBoardException::class)
    fun `when place a stone on the top of the board then throw`() {
        sut.place(0, -1, Player.WHITE)
    }

    @Test(expected = OutOfBoardException::class)
    fun `when place a stone on the right of the board then throw`() {
        sut.place(19, 0, Player.WHITE)
    }

    @Test(expected = OutOfBoardException::class)
    fun `when place a stone on the bottom of the board then throw`() {
        sut.place(0, 19, Player.WHITE)
    }
}