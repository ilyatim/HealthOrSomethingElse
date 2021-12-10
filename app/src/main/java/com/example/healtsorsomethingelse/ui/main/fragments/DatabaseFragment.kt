package com.example.healtsorsomethingelse.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.healtsorsomethingelse.data.database.mainScreen.Actions
import com.example.healtsorsomethingelse.data.database.mainScreen.UiState
import com.example.healtsorsomethingelse.data.database.mainScreen.UserDatabaseContent
import com.example.healtsorsomethingelse.databinding.DatabaseFragmentBinding
import com.example.healtsorsomethingelse.databinding.HomeFragmentBinding
import com.example.healtsorsomethingelse.extensions.ViewExtensions.gone
import com.example.healtsorsomethingelse.extensions.ViewExtensions.visible
import com.example.healtsorsomethingelse.ui.BaseBindingFragment
import com.example.healtsorsomethingelse.ui.DialogHelper
import com.example.healtsorsomethingelse.ui.main.vpComponents.DatabaseAdapter
import com.example.healtsorsomethingelse.ui.main.vpComponents.DatabaseListener
import com.example.healtsorsomethingelse.utils.BindingInflater
import com.example.healtsorsomethingelse.utils.database.DatabaseViewModel
import com.example.healtsorsomethingelse.utils.throttleFirst
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect

class DatabaseFragment : BaseBindingFragment<DatabaseFragmentBinding>() {

    override val bindingInflater: BindingInflater<DatabaseFragmentBinding>
        get() = BindingInflater { layoutInflater, container, attachToParent ->
            DatabaseFragmentBinding.inflate(layoutInflater, container, false)
        }

    private val viewModel: DatabaseViewModel by activityViewModels()
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
        DialogHelper.showBottomSheetDialogFragment(activity!!.supportFragmentManager, itemId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleUiState()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupVoiceSearch()
        setViewLayoutListener()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setViewLayoutListener() {
        binding.root.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                viewModel.sendAction(Actions.SetBarHeight(binding.searchView.height + 30))
                binding.root.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

    private fun setupVoiceSearch() {
        /*val searchManager = requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        binding.searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))*/
    }

    private fun handleUiState() {
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect {
                when (it) {
                    UiState.Idle -> { viewModel.sendAction(Actions.LoadContent) }
                    UiState.Loading -> { handleLoading() }
                    is UiState.Content -> {
                        stopLoading()
                        fetchContent(it.content, it.height)
                    }
                    is UiState.Error -> {
                        binding.errorMessage.text = it.message
                        binding.recyclerView.gone()
                        binding.progressBar.gone()
                    }
                }
            }
        }
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

    override fun onDestroyView() {
        super.onDestroyView()
        adapter = null
        //_binding = null
    }
}

