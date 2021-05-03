package com.example.healtsorsomethingelse.ui.mainActvity.fragments

import com.example.healtsorsomethingelse.data.home.Intent
import com.example.healtsorsomethingelse.data.home.UiState
import com.example.healtsorsomethingelse.utils.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {
    private val activityIntent: Channel<Intent> = Channel(Channel.UNLIMITED)

    private val _state: MutableStateFlow<UiState> = MutableStateFlow(UiState.Idle)
    val state: StateFlow<UiState>
        get() = _state

    init {
        initIntentHandle()
    }

    fun sendIntent(intent: Intent) {
        launch {
            activityIntent.send(intent)
        }
    }

    private fun initIntentHandle() {
        launch {
            activityIntent.consumeAsFlow().collect {
                when (it) {

                }
            }
        }
    }
}