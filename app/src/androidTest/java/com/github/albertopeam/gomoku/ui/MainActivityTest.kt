package com.github.albertopeam.gomoku.ui

import android.util.DisplayMetrics
import android.view.Display
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.github.albertopeam.gomoku.R
import com.github.albertopeam.gomoku.tools.TapViewActions
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.core.IsNot.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    var rule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)
    private val steps: Int = 20
    private var width: Int = 0
    private var stepWidth: Int = 0
    private var startingWidth: Int = 0
    private var halfY: Int = 0

    @Before
    fun before() {
        rule.scenario.onActivity {
            val displayMetrics = DisplayMetrics()
            it.windowManager.defaultDisplay.getMetrics(displayMetrics)
            width = displayMetrics.widthPixels
            stepWidth = width / steps
            startingWidth = stepWidth
            halfY = width / 2
        }
    }

    @Test
    fun initiallyViewsAreDisplayed() {
        onView(withId(R.id.gridView)).check(matches(isDisplayed()))
        onView(withId(R.id.playerTurn)).check(matches(isDisplayed()))
        onView(withId(R.id.winStatus)).check(matches(not(isDisplayed())))
    }

    @Test
    fun tapFiveConsecutiveInARowNoWinner() {
        (1..5).forEach {
            onView(withId(R.id.gridView)).perform(TapViewActions.tap(startingWidth * it, halfY))
        }
        onView(withId(R.id.winStatus)).check(matches(not(isDisplayed())))
    }

    @Test
    fun tapFiveBlackInARowThenBlackIsWinner() {
        (1..4).forEach {
            onView(withId(R.id.playerTurn)).check(matches(withText("Black's turn")))
            onView(withId(R.id.gridView)).perform(TapViewActions.tap(startingWidth * it, halfY))
            onView(withId(R.id.playerTurn)).check(matches(withText("White's turn")))
            onView(withId(R.id.gridView)).perform(TapViewActions.tap(startingWidth * it, halfY + stepWidth))
        }
        onView(withId(R.id.playerTurn)).check(matches(withText("Black's turn")))
        onView(withId(R.id.winStatus)).check(matches(not(isDisplayed())))
        onView(withId(R.id.gridView)).perform(TapViewActions.tap(startingWidth * 5, halfY))
        onView(withId(R.id.winStatus)).check(matches(isDisplayed()))
        onView(withId(R.id.winStatus)).check(matches(withText("Black wins")))
    }
}



