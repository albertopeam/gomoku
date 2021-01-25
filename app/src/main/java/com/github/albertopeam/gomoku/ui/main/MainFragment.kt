package com.github.albertopeam.gomoku.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.albertopeam.gomoku.databinding.MainFragmentBinding
import com.github.albertopeam.gomoku.domain.Board
import com.github.albertopeam.gomoku.domain.Player
import com.github.albertopeam.gomoku.domain.Position

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
        // TODO: Remove demo board
        val demoBoard = Board()
        demoBoard.place(Position(0,0), Player.WHITE)
        demoBoard.place(Position(0,1), Player.BLACK)
        demoBoard.place(Position(1,0), Player.WHITE)
        demoBoard.place(Position(18,18), Player.BLACK)
        demoBoard.place(Position(18,17), Player.WHITE)
        binding.gridView.board = demoBoard
    }

}