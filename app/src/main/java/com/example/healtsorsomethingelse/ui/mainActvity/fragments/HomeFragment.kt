package com.example.healtsorsomethingelse.ui.mainActvity.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.healtsorsomethingelse.R
import com.example.healtsorsomethingelse.data.home.Intent
import com.example.healtsorsomethingelse.data.home.Statistics
import com.example.healtsorsomethingelse.data.home.UiState
import com.example.healtsorsomethingelse.databinding.HomeFragmentBinding
import com.example.healtsorsomethingelse.ui.mainActvity.rvComponents.adapters.Adapter
import com.example.healtsorsomethingelse.utils.BaseFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment() {

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

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
        if (this::adapter.isInitialized) {
            adapter.updateList(list)
        } else {
            adapter = Adapter(layoutInflater, mutableListOf())
            //TODO: handle recycler
            //binding.recyclerView
        }
    }

    private fun handleLoading() {
        TODO("Not yet implemented")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}