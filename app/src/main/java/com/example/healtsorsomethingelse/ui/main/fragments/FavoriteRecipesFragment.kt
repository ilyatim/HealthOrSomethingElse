package com.example.healtsorsomethingelse.ui.main.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.healtsorsomethingelse.R
import com.example.healtsorsomethingelse.data.database.UiState
import com.example.healtsorsomethingelse.databinding.FavoriteRecipesFragmentBinding
import com.example.healtsorsomethingelse.utils.BaseFragment
import com.example.healtsorsomethingelse.utils.database.AllRecipesViewModel
import com.example.healtsorsomethingelse.utils.database.FavoriteRecipesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteRecipesFragment : BaseFragment() {

    private val viewModel: FavoriteRecipesViewModel by viewModels()
    private var _binding: FavoriteRecipesFragmentBinding? = null
    private val binding: FavoriteRecipesFragmentBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FavoriteRecipesFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        handleUiState()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun handleUiState() {
        launch {
            viewModel.state.collect {
                when (it) {
                    is UiState.Content -> {}
                    UiState.Idle -> {}
                    UiState.Loading -> {}
                }
            }
        }
    }

    companion object {
        fun newInstance() = FavoriteRecipesFragment()
    }

}