package com.example.healtsorsomethingelse.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.healtsorsomethingelse.data.database.recipes.RecipeCell
import com.example.healtsorsomethingelse.data.database.recipes.UiAction
import com.example.healtsorsomethingelse.data.database.recipes.UiState
import com.example.healtsorsomethingelse.databinding.FavoriteRecipesFragmentBinding
import com.example.healtsorsomethingelse.extensions.ViewExtensions.gone
import com.example.healtsorsomethingelse.extensions.ViewExtensions.visible
import com.example.healtsorsomethingelse.ui.DialogHelper
import com.example.healtsorsomethingelse.ui.main.rvComponents.adapters.database.OnRecipeItemListener
import com.example.healtsorsomethingelse.ui.main.rvComponents.adapters.database.RecipesAdapter
import com.example.healtsorsomethingelse.utils.BaseFragment
import com.example.healtsorsomethingelse.utils.database.FavoriteRecipesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteRecipesFragment : BaseFragment() {

    private val viewModel: FavoriteRecipesViewModel by activityViewModels()
    private var _binding: FavoriteRecipesFragmentBinding? = null
    private val binding: FavoriteRecipesFragmentBinding
        get() = _binding!!

    private lateinit var adapter: RecipesAdapter
    private val onItemClickListener = OnRecipeItemListener {
        showBottomSheetDialog(it)
    }

    private fun showBottomSheetDialog(id: Int) {
        activity?.let {
            DialogHelper.showBottomSheetDialogFragment(it.supportFragmentManager, id)
        }
    }

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
                if (_binding == null) return@collect
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
                    is UiState.Error -> {
                        handleError(it.error)
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
            binding.recyclerView.itemAnimator = DefaultItemAnimator()
            binding.recyclerView.adapter = adapter
        }

        binding.recyclerView.visible()
        binding.errorTextView.gone()
        binding.progressBar.gone()
    }

    private fun initLoading() {
        viewModel.sendAction(UiAction.Loading)
    }

    private fun handleLoading() {
        binding.errorTextView.gone()
        binding.recyclerView.gone()
        binding.progressBar.visible()
    }

    private fun handleError(error: String?) {
        binding.errorTextView.text = error
        binding.recyclerView.gone()
        binding.progressBar.gone()
        binding.errorTextView.visible()
    }

    companion object {
        fun newInstance() = FavoriteRecipesFragment()
    }

}