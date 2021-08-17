package com.github.albertopeam.gomoku.data

import com.github.albertopeam.gomoku.domain.*

class GomokuBoardFactory {
    companion object {
        fun make(): Pair<Board, BoardState> {
            val boardData = BoardData()
            val board: Board = boardData
            val boardState: BoardState = boardData
            return Pair(board, boardState)
        }
    }
}
