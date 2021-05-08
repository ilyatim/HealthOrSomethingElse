package com.example.healtsorsomethingelse.ui.main.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.healtsorsomethingelse.data.database.RecipeCell
import com.example.healtsorsomethingelse.data.database.UiAction
import com.example.healtsorsomethingelse.data.database.UiState
import com.example.healtsorsomethingelse.databinding.AllRecipesFragmentBinding
import com.example.healtsorsomethingelse.databinding.ListWithProgressBarLayoutBinding
import com.example.healtsorsomethingelse.extensions.ViewExtensions.gone
import com.example.healtsorsomethingelse.extensions.ViewExtensions.visible
import com.example.healtsorsomethingelse.ui.main.rvComponents.adapters.database.OnRecipeItemListener
import com.example.healtsorsomethingelse.ui.main.rvComponents.adapters.database.RecipesAdapter
import com.example.healtsorsomethingelse.ui.main.vpComponents.RecipesFragmentAdapter
import com.example.healtsorsomethingelse.utils.BaseFragment
import com.example.healtsorsomethingelse.utils.database.AllRecipesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllRecipesFragment : BaseFragment() {

    private val viewModel: AllRecipesViewModel by viewModels()

    private var _binding: AllRecipesFragmentBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var adapter: RecipesAdapter

    private val onItemClickListener = OnRecipeItemListener {
        showBottomSheetDialog(it)
    }

    private fun showBottomSheetDialog(id: Int) {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AllRecipesFragmentBinding.inflate(inflater, container, false)
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
                    UiState.Idle -> {
                        initLoading()
                    }
                    UiState.Loading -> {
                        handleLoading()
                    }
                    is UiState.Content -> {
                        fetchContent(it.items)
                    }
                }
            }
        }
    }

    private fun fetchContent(items: List<RecipeCell>) {
        if (this::adapter.isInitialized) {
            adapter.updateList(items)
        } else {
            adapter = RecipesAdapter(layoutInflater, onItemClickListener, items.toMutableList())
            binding.recyclerView.adapter = adapter
        }

        binding.recyclerView.visible()
        binding.progressBar.gone()
    }

    private fun handleLoading() {
        binding.recyclerView.gone()
        binding.progressBar.visible()
    }

    private fun initLoading() {
        viewModel.sendAction(UiAction.Loading)
    }

    companion object {
        fun newInstance() = AllRecipesFragment()
    }
}
