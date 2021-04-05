package com.github.albertopeam.gomoku.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private val _playerTurn = MutableLiveData("Tilt")
    val playerTurn: LiveData<String> = _playerTurn
}