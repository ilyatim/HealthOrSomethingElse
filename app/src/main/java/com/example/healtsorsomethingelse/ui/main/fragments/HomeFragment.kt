package com.example.healtsorsomethingelse.ui.main.fragments

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.healtsorsomethingelse.R
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
import com.google.android.material.transition.MaterialSharedAxis
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeFragmentViewModel by activityViewModels()

    private var adapter: Adapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        handleUiState()
        super.onCreate(savedInstanceState)

        /*exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleLoading()
        handleButtonClick()
        handleRatingBar()
    }

    private fun handleRatingBar() {
        binding.feelingLayout.feelingRatingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            Log.d("HomeFragmentTag", "rating - $rating, from user - $fromUser")
        }
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
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect {
                when (it) {
                    UiState.Idle -> {}
                    UiState.Loading -> handleLoading()
                    is UiState.Content -> fetchContent(it.todayRate, it.list, it.availabilityOfNotifications)
                }
            }
        }
    }

    override fun onResume() {
        viewModel.sendIntent(HomeIntent.InitLoading)
        super.onResume()
    }

    private fun fetchContent(todayRate: Int, list: List<Statistics>, availabilityOfNotification: Boolean) {
        binding.progressBar.gone()
        binding.feelingLayout.feelingRatingBar.rating = todayRate.toFloat()
        binding.headerLayout.notificationImageButton.setImageDrawable(getNotificationDrawable(availabilityOfNotification))

        adapter?.updateList(list) ?: kotlin.run {
            adapter = Adapter(layoutInflater, list.toMutableList())
        }

        if (binding.recyclerViewLayout.recyclerView.adapter == null) {
            binding.recyclerViewLayout.recyclerView.adapter = adapter
        }
    }

    private fun getNotificationDrawable(availabilityOfNotification: Boolean): Drawable? {
        val resId: Int = if (availabilityOfNotification) {
            R.drawable.ic_new_notification
        } else {
            R.drawable.notification
        }
        return AppCompatResources.getDrawable(requireContext(), resId)
    }

    private fun handleLoading() {
        binding.progressBar.visible()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        adapter = null
    }
}