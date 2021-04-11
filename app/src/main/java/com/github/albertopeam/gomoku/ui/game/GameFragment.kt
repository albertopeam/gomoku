package com.github.albertopeam.gomoku.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.albertopeam.gomoku.databinding.GameFragmentBinding
import com.github.albertopeam.gomoku.domain.Board
import com.github.albertopeam.gomoku.domain.Game
import com.github.albertopeam.gomoku.domain.GomokuRules


class GameFragment : Fragment() {

    companion object {
        fun newInstance() = GameFragment()
    }

    private lateinit var binding: GameFragmentBinding
    private lateinit var viewModel: GameViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = GameFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val board = Board()
        val rules = GomokuRules()
        val game = Game(board, rules)
        viewModel = ViewModelProvider(this, GameViewModelFactory(game)).get(GameViewModel::class.java)

        // TODO: remove game from gridView as much as possible
        binding.gridView.game = game
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}