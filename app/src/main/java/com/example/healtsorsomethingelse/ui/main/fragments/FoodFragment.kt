package com.example.healtsorsomethingelse.ui.main.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.healtsorsomethingelse.utils.database.FoodViewModel
import com.example.healtsorsomethingelse.databinding.RecipesFragmentBinding
import com.example.healtsorsomethingelse.utils.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FoodFragment : BaseFragment() {
    private var _binding: RecipesFragmentBinding? = null
    private val binding: RecipesFragmentBinding
        get() = _binding!!

    private val viewModel: FoodViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RecipesFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        handleUiState()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun handleUiState() {
        launch {  }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance() = FoodFragment()
    }
}
