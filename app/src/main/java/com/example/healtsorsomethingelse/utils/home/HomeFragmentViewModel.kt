package com.example.healtsorsomethingelse.utils.home

import com.example.core.utils.BaseViewModel
import com.example.healtsorsomethingelse.data.home.Action
import com.example.healtsorsomethingelse.data.home.HomeRepository
import com.example.healtsorsomethingelse.data.home.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val repo: HomeRepository
) : BaseViewModel<UiState, Action, Unit>() {
    override val uiState: MutableStateFlow<UiState>
        get() = MutableStateFlow(UiState.Idle)

    private fun initContent() {
        setState(UiState.Loading)
        launch {
            val state = UiState.Content(
                4,
                repo.getAllStatistic(),
                repo.checkNotificationAvailability()
            )
            setState(state)
        }
    }

    override fun collectAction(action: Action) {
        when (action) {
            Action.InitLoading -> initContent()
        }
    }
}