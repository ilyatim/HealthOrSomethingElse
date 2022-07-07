package com.example.healtsorsomethingelse.ui.main.fragments

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.core.ui.AbsBindingFragment
import com.example.core.ui.AbsFragment
import com.example.core.ui.ViewInflater
import com.example.core.utils.throttleFirst
import com.example.healtsorsomethingelse.data.database.mainScreen.Actions
import com.example.healtsorsomethingelse.data.database.mainScreen.UiState
import com.example.healtsorsomethingelse.data.database.mainScreen.UserDatabaseContent
import com.example.healtsorsomethingelse.databinding.DatabaseFragmentBinding
import com.example.healtsorsomethingelse.extensions.ViewExtensions.gone
import com.example.healtsorsomethingelse.extensions.ViewExtensions.visible
import com.example.healtsorsomethingelse.ui.DialogHelper
import com.example.healtsorsomethingelse.ui.main.vpComponents.DatabaseAdapter
import com.example.healtsorsomethingelse.ui.main.vpComponents.DatabaseListener
import com.example.healtsorsomethingelse.utils.database.DatabaseViewModel
import kotlinx.coroutines.MainScope

class DatabaseFragment : AbsFragment<DatabaseFragmentBinding, UiState, Actions, Unit, DatabaseViewModel>() {
    override val viewInflater: ViewInflater<DatabaseFragmentBinding>
        get() = ViewInflater { layoutInflater, container, _ ->
            DatabaseFragmentBinding.inflate(layoutInflater, container, false)
        }
    override val TAG: String
        get() = TODO("Not yet implemented")

    override val viewModel: DatabaseViewModel by activityViewModels()

    private var adapter: DatabaseAdapter? = null

    private val buttonClickHandler: (Int) -> Unit = throttleFirst(
        500,
        MainScope(),
        this::showBottomSheetDialog
    )

    private val clickListener = object : DatabaseListener {
        override fun onSubRecyclerTopicClick() {
            TODO("Not yet implemented")
        }

        override fun onChapterClick() {
            TODO("Not yet implemented")
        }

        override fun onSubRecyclerCellClick(itemId: Int) {
            activity?.let { buttonClickHandler(itemId) }
        }
    }

    private fun showBottomSheetDialog(itemId: Int) {
        DialogHelper.showBottomSheetDialogFragment(requireActivity().supportFragmentManager, itemId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupVoiceSearch()
        setViewLayoutListener()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setViewLayoutListener() {
        binding.root.viewTreeObserver.addOnGlobalLayoutListener(
            object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    viewModel.applyAction(Actions.SetBarHeight(binding.searchView.height + 30))
                    binding.root.viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
            }
        )
    }

    private fun setupVoiceSearch() {
        /*val searchManager = requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        binding.searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))*/
    }

    private fun stopLoading() {
        binding.progressBar.gone()
        binding.errorMessage.gone()
        binding.recyclerView.visible()
    }

    private fun fetchContent(content: List<UserDatabaseContent>, height: Int) {
        adapter?.updateList(content) ?: kotlin.run {
            adapter = DatabaseAdapter(layoutInflater, content.toMutableList(), clickListener)
            adapter?.setTopMargin(height)
            binding.recyclerView.adapter = adapter
        }
    }

    private fun handleLoading() {
        binding.recyclerView.gone()
        binding.errorMessage.gone()
        binding.progressBar.visible()
    }

    override fun onDestroy() {
        adapter = null
        super.onDestroy()
    }

    override fun collectUiState(state: UiState) = when (state) {
        is UiState.Content -> {
            stopLoading()
            fetchContent(state.content, state.height)
        }
        is UiState.Error -> {
            binding.errorMessage.text = state.message
            binding.recyclerView.gone()
            binding.progressBar.gone()
        }
        UiState.Idle -> viewModel.applyAction(Actions.LoadContent)
        UiState.Loading -> handleLoading()
    }

}

