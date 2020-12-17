package com.github.albertopeam.gomoku.domain

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import java.lang.Exception

class BoardTest {

    private lateinit var sut: Board

    @Before
    fun setUp() {
        sut = Board()
    }

    // stones

    @Test
    fun `when create board then number of stones is zero`() {
        assertEquals(0, sut.stones())
    }

    // get

    @Test
    fun `when place stones in a position then gets added`() {
        sut.place(Position(0, 0), Player.WHITE)
        assertEquals(1, sut.stones())
        assertEquals(Player.WHITE, sut.get(Position(0, 0)))

        sut.place(Position(Board.size - 1, Board.size - 1), Player.BLACK)
        assertEquals(2, sut.stones())
        assertEquals(Player.BLACK, sut.get(Position(Board.size - 1, Board.size - 1)))
    }

    // place

    @Test
    fun `when place white stone in a position then player in this position is white`() {
        assertEquals(Player.EMPTY, sut.get(Position(1, 1)))

        sut.place(Position(1, 1), Player.WHITE)

        assertEquals(Player.WHITE, sut.get(Position(1, 1)))
    }

    @Test
    fun `when place a stone in a occuppied intersection position then throw`() {
        sut.place(Position(1, 1), Player.WHITE)

        assertEquals(Player.WHITE, sut.get(Position(1, 1)))

        var numOfExceptions = 0
        try {
            sut.place(Position(1, 1), Player.BLACK)
        } catch (e: SpaceOccupiedException) {
            numOfExceptions++
        }
        try {
            sut.place(Position(1, 1), Player.WHITE)
        } catch (e: SpaceOccupiedException) {
            numOfExceptions++
        }
        assertEquals(2, numOfExceptions)
    }

    @Test(expected = OutOfBoardException::class)
    fun `when place a stone on the left of the board then throw`() {
        sut.place(Position(-1, 0), Player.WHITE)
    }

    @Test(expected = OutOfBoardException::class)
    fun `when place a stone on the top of the board then throw`() {
        sut.place(Position(0, -1), Player.WHITE)
    }

    @Test(expected = OutOfBoardException::class)
    fun `when place a stone on the right of the board then throw`() {
        sut.place(Position(Board.size, 0), Player.WHITE)
    }

    @Test(expected = OutOfBoardException::class)
    fun `when place a stone on the bottom of the board then throw`() {
        sut.place(Position(0, Board.size), Player.WHITE)
    }

    @Test
    fun `when place a stone out the board then placed stones are zero`() {
        var stones: Int? = null
        try {
            sut.place(Position(-1, 0), Player.WHITE)
        } catch (e: OutOfBoardException){
            stones = sut.stones()
        } finally {
            assertEquals(0, stones)
        }
    }
}