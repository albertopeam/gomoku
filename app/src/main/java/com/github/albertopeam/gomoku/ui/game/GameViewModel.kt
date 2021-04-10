package com.github.albertopeam.gomoku.ui.game

import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {
    //TODO: connect to be able to read player
    private val _playerTurn = MutableLiveData("Black's turn")
    val playerTurn: LiveData<String> = _playerTurn

    val tap: (Int, Int) -> Unit = { row, column ->
        //TODO: have here the game or board...
        //TODO: pending to read rows and columns from this viewModel
        Log.d("lambda is called", "lambda is called")
    }
}