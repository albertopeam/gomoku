package com.github.albertopeam.gomoku.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.albertopeam.gomoku.R
import com.github.albertopeam.gomoku.ui.game.GameFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, GameFragment.newInstance())
                .commitNow()
        }
    }
}