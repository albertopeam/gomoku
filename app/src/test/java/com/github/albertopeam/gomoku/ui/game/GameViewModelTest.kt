package com.github.albertopeam.gomoku.ui.game

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.albertopeam.gomoku.await
import com.github.albertopeam.gomoku.domain.Board
import com.github.albertopeam.gomoku.domain.Game
import com.github.albertopeam.gomoku.domain.GomokuRules
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GameViewModelTest {
    private lateinit var sut: GameViewModel
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        val game = Game(Board(), GomokuRules())
        sut = GameViewModel(game)
    }

    @Test
    fun `when ask player turn then is correctly formatted`() {
        assertThat(sut.playerTurn.await(), equalTo("Black's turn"))
    }

    @Test
    fun `when ask player turn twice then is correctly formatted`() {
        assertThat(sut.playerTurn.await(), equalTo("Black's turn"))
        sut.tap.invoke(0, 0)
        assertThat(sut.playerTurn.await(), equalTo("White's turn"))
        sut.tap.invoke(0, 1)
        assertThat(sut.playerTurn.await(), equalTo("Black's turn"))
    }

    //TODO: min 10 video 6
}