package com.github.albertopeam.gomoku.tools

import android.view.InputDevice
import android.view.MotionEvent
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.GeneralClickAction
import androidx.test.espresso.action.Press
import androidx.test.espresso.action.Tap

class TapViewActions {
    companion object {
        fun tap(x: Int, y: Int): ViewAction {
            return GeneralClickAction(
                Tap.SINGLE,
                { view ->
                    val screenPos = IntArray(2)
                    view.getLocationOnScreen(screenPos)
                    val screenX = (screenPos[0] + x).toFloat()
                    val screenY = (screenPos[1] + y).toFloat()
                    floatArrayOf(screenX, screenY)
                },
                Press.FINGER,
                InputDevice.SOURCE_MOUSE,
                MotionEvent.BUTTON_PRIMARY
            )
        }
    }
}