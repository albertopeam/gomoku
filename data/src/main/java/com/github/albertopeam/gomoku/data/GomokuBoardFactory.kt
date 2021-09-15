package com.github.albertopeam.gomoku.data

class GomokuBoardFactory {
    companion object {
        fun make(): GomokuBoard {
            return BoardData()
        }
    }
}