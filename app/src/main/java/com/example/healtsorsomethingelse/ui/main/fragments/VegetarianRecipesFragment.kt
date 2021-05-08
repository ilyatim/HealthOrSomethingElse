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
import com.example.healtsorsomethingelse.databinding.VegetarianRecipesFragmentBinding
import com.example.healtsorsomethingelse.utils.BaseFragment
import com.example.healtsorsomethingelse.utils.database.AllRecipesViewModel
import com.example.healtsorsomethingelse.utils.database.VegetarianRecipesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class VegetarianRecipesFragment : BaseFragment() {

    private val viewModel: VegetarianRecipesViewModel by viewModels()
    private var _binding: VegetarianRecipesFragmentBinding? = null
    private val binding: VegetarianRecipesFragmentBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = VegetarianRecipesFragmentBinding.inflate(
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
        fun newInstance() = VegetarianRecipesFragment()
    }

}