package com.example.healtsorsomethingelse.utils.home

import com.example.healtsorsomethingelse.data.home.HomeRepository
import com.example.healtsorsomethingelse.data.home.Action
import com.example.healtsorsomethingelse.data.home.UiState
import com.example.healtsorsomethingelse.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val repo: HomeRepository
) : BaseViewModel<UiState, Action>(UiState.Idle) {

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