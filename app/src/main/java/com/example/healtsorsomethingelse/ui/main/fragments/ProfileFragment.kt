package com.example.healtsorsomethingelse.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.healtsorsomethingelse.data.profile.UiAction
import com.example.healtsorsomethingelse.data.profile.UiState
import com.example.healtsorsomethingelse.databinding.ProfileFragmentBinding
import com.example.healtsorsomethingelse.utils.BaseFragment
import com.example.healtsorsomethingelse.utils.ProfileFragmentViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProfileFragment : BaseFragment() {

    private var _binding: ProfileFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileFragmentViewModel by viewModels()

    private fun handleUiState() {
        launch {
            viewModel.state.collect {
                when (it) {
                    UiState.Idle -> viewModel.sendAction(UiAction.Loading)
                    UiState.Loading -> TODO()
                    is UiState.Content -> TODO()
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ProfileFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}