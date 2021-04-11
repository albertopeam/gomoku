package com.github.albertopeam.gomoku.ui.game

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.albertopeam.gomoku.await
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
        sut = GameViewModel()
    }

    @Test
    fun `when ask player turn then is correctly formatted`() {
        assertThat(sut.playerTurn.await(), equalTo("Black's turn"))
    }

    //TODO: inject game
    //TODO: turns test,

    //TODO: min 10 video 6
}