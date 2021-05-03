package com.github.albertopeam.gomoku.domain

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.Before

class BoardDataTest {

    private lateinit var sut: BoardData

    @Before
    fun setUp() {
        sut = BoardData()
    }
    // last row

    @Test
    fun `given board initialized then is last row matches rows minus one`() {
        assertThat(sut.lastRow, equalTo(18))
    }

    // last column

    @Test
    fun `given board initialized then is last column matches columns minus one`() {
        assertThat(sut.lastColumn, equalTo(18))
    }

    // stones

    @Test
    fun `when create board then number of stones is zero`() {
        assertThat(sut.stones(), equalTo(0))
    }

    // get

    @Test
    fun `when place stones in a position then gets added`() {
        sut.place(Position(0, 0), Player.WHITE)
        assertThat(sut.stones(), equalTo(1))
        assertThat(sut.get(Position(0, 0)), equalTo(Player.WHITE))

        sut.place(Position(sut.rows - 1, sut.columns - 1), Player.BLACK)
        assertThat(sut.stones(), equalTo(2))
        assertThat(sut.get(Position(sut.rows - 1, sut.columns - 1)), equalTo(Player.BLACK))
    }

    // place

    @Test
    fun `when place white stone in a position then player in this position is white`() {
        assertThat(sut.get(Position(1, 1)), equalTo(Player.EMPTY))

        sut.place(Position(1, 1), Player.WHITE)

        assertThat(sut.get(Position(1, 1)), equalTo(Player.WHITE))
    }

    @Test
    fun `when place a stone in a occuppied intersection position then throw`() {
        sut.place(Position(1, 1), Player.WHITE)

        assertThat(sut.get(Position(1, 1)), equalTo(Player.WHITE))

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
        assertThat(numOfExceptions, equalTo(2))
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
        sut.place(Position(sut.rows, 0), Player.WHITE)
    }

    @Test(expected = OutOfBoardException::class)
    fun `when place a stone on the bottom of the board then throw`() {
        sut.place(Position(0, sut.columns), Player.WHITE)
    }

    @Test
    fun `when place a stone out the board then placed stones are zero`() {
        var stones: Int? = null
        try {
            sut.place(Position(-1, 0), Player.WHITE)
        } catch (e: OutOfBoardException){
            stones = sut.stones()
        } finally {
            assertThat(stones, equalTo(0))
        }
    }
}