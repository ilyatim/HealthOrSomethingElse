package com.example.healtsorsomethingelse.ui.mainActivity.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.healtsorsomethingelse.data.home.Intent
import com.example.healtsorsomethingelse.data.home.Statistics
import com.example.healtsorsomethingelse.data.home.UiState
import com.example.healtsorsomethingelse.databinding.HomeFragmentBinding
import com.example.healtsorsomethingelse.ui.mainActivity.rvComponents.adapters.Adapter
import com.example.healtsorsomethingelse.utils.BaseFragment
import com.example.healtsorsomethingelse.utils.home.HomeFragmentViewModel
import dagger.Provides
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeFragmentViewModel by viewModels()

    private lateinit var adapter: Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleUiState()
    }

    private fun handleUiState() {
        launch {
            viewModel.state.collect {
                when (it) {
                    UiState.Idle -> viewModel.sendIntent(Intent.InitLoading)
                    UiState.Loading -> handleLoading()
                    is UiState.Content -> fetchContent(it.todayRate, it.list)
                }
            }
        }
    }

    private fun fetchContent(todayRate: Int, list: List<Statistics>) {
        binding.feelingLayout.feelingRatingBar.rating = todayRate.toFloat()
        if (this::adapter.isInitialized) {
            adapter.updateList(list)
        } else {
            adapter = Adapter(layoutInflater, mutableListOf())
            binding.recyclerViewLayout.recyclerView.adapter = adapter
        }
    }

    private fun handleLoading() {
        viewModel.sendIntent(Intent.InitLoading)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}