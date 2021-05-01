package com.github.albertopeam.gomoku.ui.game

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.albertopeam.gomoku.await
import com.github.albertopeam.gomoku.domain.*
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GameViewModelTest {
    private lateinit var sut: GameViewModel
    private lateinit var game: Game
    private lateinit var board: BoardData
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        board = BoardData()
        game = Game(board, GomokuRules())
        sut = GameViewModel(game)
    }

    // init

    @Test
    fun `when ask player turn then is correctly formatted`() {
        assertThat(sut.playerTurn.await(), equalTo("Black's turn"))
    }

    // turn

    @Test
    fun `when ask player turn twice then is correctly formatted`() {
        assertThat(sut.playerTurn.await(), equalTo("Black's turn"))
        sut.tap.invoke(0, 0)
        assertThat(sut.playerTurn.await(), equalTo("White's turn"))
        sut.tap.invoke(0, 1)
        assertThat(sut.playerTurn.await(), equalTo("Black's turn"))
    }

    // win status

    @Test
    fun `when tap position for winning game then is winner for current player`() {
        (0..3).forEach { board.place(Position(0, it), Player.WHITE) }

        sut.tap(0 ,5)
        sut.tap(0 ,4)

        assertThat(sut.winStatus.await(), equalTo("White wins"))
    }

    @Test
    fun `when tap position for not winning game then is no winner for current player`() {
        (0..3).forEach { board.place(Position(0, it), Player.WHITE) }

        sut.tap(0 ,5)

        assertThat(sut.winStatus.await(), equalTo(""))
    }
}