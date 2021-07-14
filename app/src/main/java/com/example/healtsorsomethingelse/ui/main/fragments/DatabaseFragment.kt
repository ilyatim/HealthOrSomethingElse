package com.example.healtsorsomethingelse.ui.main.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.healtsorsomethingelse.R
import com.example.healtsorsomethingelse.data.database.mainScreen.Actions
import com.example.healtsorsomethingelse.data.database.mainScreen.UiState
import com.example.healtsorsomethingelse.databinding.DatabaseFragmentBinding
import com.example.healtsorsomethingelse.extensions.ContextExtensions.showShortToast
import com.example.healtsorsomethingelse.extensions.ViewExtensions.gone
import com.example.healtsorsomethingelse.extensions.ViewExtensions.visible
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

    private var _binding: DatabaseFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DatabaseViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DatabaseFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.state.collect {
                when (it) {
                    UiState.Idle -> { viewModel.sendAction(Actions.LoadContent) }
                    UiState.Loading -> { handleLoading() }
                    is UiState.Error -> {
                        showToast(it.message)
                        binding.recyclerView.gone()
                        binding.progressBar.gone()
                    }
                }
            }
        }
            /*exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
            reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)*/
    }

    private fun showToast(message: String) {
        requireActivity().showShortToast(message)
    }

    private fun handleLoading() {
        binding.recyclerView.gone()
        binding.progressBar.visible()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

