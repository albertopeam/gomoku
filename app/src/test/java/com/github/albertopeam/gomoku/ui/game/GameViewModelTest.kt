package com.github.albertopeam.gomoku.ui.game

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.albertopeam.gomoku.await
import com.github.albertopeam.gomoku.domain.*
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GameViewModelTest {
    private lateinit var sut: GameViewModel
    private lateinit var game: Game

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        game = GomokuGameFactory.make()
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
        sut.tap(0 ,0)
        sut.tap(1 ,0)
        sut.tap(0 ,1)
        sut.tap(1 ,1)
        sut.tap(0 ,2)
        sut.tap(1 ,2)
        sut.tap(0 ,3)
        sut.tap(1 ,3)
        sut.tap(0 ,5)
        sut.tap(1 ,4)

        assertThat(sut.winStatus.await(), equalTo("White wins"))
    }

    @Test
    fun `when tap position for not winning game then is no winner for current player`() {
        sut.tap(0 ,0)
        sut.tap(1 ,0)
        sut.tap(0 ,1)
        sut.tap(1 ,1)
        sut.tap(0 ,2)
        sut.tap(1 ,2)
        sut.tap(0 ,3)
        sut.tap(1 ,3)

        assertThat(sut.winStatus.await(), equalTo(""))
    }
}