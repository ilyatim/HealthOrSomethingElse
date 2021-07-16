package com.example.healtsorsomethingelse.ui.main.fragments

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.healtsorsomethingelse.R
import com.example.healtsorsomethingelse.data.database.mainScreen.Actions
import com.example.healtsorsomethingelse.data.database.mainScreen.UiState
import com.example.healtsorsomethingelse.data.database.mainScreen.UserDatabaseContent
import com.example.healtsorsomethingelse.databinding.DatabaseFragmentBinding
import com.example.healtsorsomethingelse.extensions.ContextExtensions.showShortToast
import com.example.healtsorsomethingelse.extensions.ViewExtensions.gone
import com.example.healtsorsomethingelse.extensions.ViewExtensions.visible
import com.example.healtsorsomethingelse.ui.main.vpComponents.DatabaseAdapter
import com.example.healtsorsomethingelse.ui.main.vpComponents.DatabaseListener
import com.example.healtsorsomethingelse.ui.main.vpComponents.FragmentAdapter
import com.example.healtsorsomethingelse.utils.CustomOnTabSelectedListener
import com.example.healtsorsomethingelse.utils.database.DatabaseViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.transition.MaterialSharedAxis
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DatabaseFragment : Fragment() {

    private var searchBarHeight: Int = 0
    private var _binding: DatabaseFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DatabaseViewModel by activityViewModels()
    private lateinit var adapter: DatabaseAdapter

    private val onGlobalLayoutListener = ViewTreeObserver.OnGlobalLayoutListener {
        if (_binding == null) return@OnGlobalLayoutListener
        searchBarHeight = binding.searchView.height + 30
    }

    private val clickListener = object : DatabaseListener {
        override fun onSubRecyclerTopicClick() {
            TODO("Not yet implemented")
        }

        override fun onChapterClick() {
            TODO("Not yet implemented")
        }

        override fun onSubRecyclerCellClick() {
            TODO("Not yet implemented")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DatabaseFragmentBinding.inflate(inflater, container, false)
        return binding.root
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
        binding.root.viewTreeObserver.addOnGlobalLayoutListener(onGlobalLayoutListener)
    }

    private fun setupVoiceSearch() {
        /*val searchManager = requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        binding.searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))*/
    }

    private fun handleUiState() {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.state.collect {
                if (_binding == null) return@collect
                when (it) {
                    UiState.Idle -> { viewModel.sendAction(Actions.LoadContent) }
                    UiState.Loading -> { handleLoading() }
                    is UiState.Content -> {
                        stopLoading()
                        fetchContent(it.content)
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

    private fun fetchContent(content: List<UserDatabaseContent>) {
        if (this::adapter.isInitialized) {
            adapter.updateList(content)
        } else {
            adapter = DatabaseAdapter(layoutInflater, content.toMutableList(), clickListener)
            adapter.setTopMargin(searchBarHeight)
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
        binding.root.viewTreeObserver.removeOnGlobalLayoutListener(onGlobalLayoutListener)
        _binding = null
    }

}

