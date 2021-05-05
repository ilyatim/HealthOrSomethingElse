package com.example.healtsorsomethingelse.ui.main.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.healtsorsomethingelse.data.home.HomeIntent
import com.example.healtsorsomethingelse.data.home.Statistics
import com.example.healtsorsomethingelse.data.home.UiState
import com.example.healtsorsomethingelse.databinding.HomeFragmentBinding
import com.example.healtsorsomethingelse.extensions.ViewExtensions.gone
import com.example.healtsorsomethingelse.extensions.ViewExtensions.visible
import com.example.healtsorsomethingelse.ui.main.rvComponents.adapters.home.Adapter
import com.example.healtsorsomethingelse.ui.notification.NotificationActivity
import com.example.healtsorsomethingelse.ui.settings.SettingsActivity
import com.example.healtsorsomethingelse.ui.workout.WorkoutActivity
import com.example.healtsorsomethingelse.utils.BaseFragment
import com.example.healtsorsomethingelse.utils.home.HomeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

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

    override fun onCreate(savedInstanceState: Bundle?) {
        handleUiState()
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleButtonClick()
        handleRatingBar()
    }

    private fun handleRatingBar() {
    }

    private fun handleButtonClick() {
        binding.headerLayout.settingsImageButton.setOnClickListener {
            handleSettingsButton()
        }
        binding.headerLayout.workoutImageButton.setOnClickListener {
            handleWorkoutImageButton()
        }
        binding.headerLayout.notificationImageButton.setOnClickListener {
            handleNotificationImageButton()
        }
    }

    private fun handleSettingsButton() {
        this.startActivity(Intent(context, SettingsActivity::class.java))
    }

    private fun handleWorkoutImageButton() {
        this.startActivity(Intent(context, WorkoutActivity::class.java))
    }

    private fun handleNotificationImageButton() {
        this.startActivity(Intent(context, NotificationActivity::class.java))
    }

    private fun handleUiState() {
        launch {
            viewModel.state.collect {
                when (it) {
                    UiState.Idle -> viewModel.sendIntent(HomeIntent.InitLoading)
                    UiState.Loading -> handleLoading()
                    is UiState.Content -> fetchContent(it.todayRate, it.list)
                }
            }
        }
    }

    private fun fetchContent(todayRate: Int, list: List<Statistics>) {
        binding.progressBar.gone()
        binding.feelingLayout.feelingRatingBar.rating = todayRate.toFloat()
        if (this::adapter.isInitialized) {
            adapter.updateList(list)
        } else {
            adapter = Adapter(layoutInflater, list.toMutableList())
            binding.recyclerViewLayout.recyclerView.adapter = adapter
        }
    }

    private fun handleLoading() {
        binding.progressBar.visible()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}