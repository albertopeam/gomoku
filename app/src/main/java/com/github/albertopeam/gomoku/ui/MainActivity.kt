package com.github.albertopeam.gomoku.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.github.albertopeam.gomoku.R
import com.github.albertopeam.gomoku.ui.game.GameFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            replaceGameFragment()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_activity_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.restart -> {
                replaceGameFragment()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun replaceGameFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, GameFragment.newInstance())
            .commitNow()
    }
}