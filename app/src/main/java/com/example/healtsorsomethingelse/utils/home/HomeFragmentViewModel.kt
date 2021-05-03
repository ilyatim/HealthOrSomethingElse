package com.example.healtsorsomethingelse.utils.home

import android.util.Log
import com.example.healtsorsomethingelse.data.home.HomeRepository
import com.example.healtsorsomethingelse.data.home.Intent
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
class HomeFragmentViewModel @Inject constructor(private val repo: HomeRepository) : BaseViewModel() {
    private val _state: MutableStateFlow<UiState> = MutableStateFlow(UiState.Idle)
    val state: StateFlow<UiState>
        get() = _state

    private val intent: Channel<Intent> = Channel(Channel.UNLIMITED)

    init {
        handleIntent()
    }

    fun sendIntent(intent: Intent) {
        launch {
            this@HomeFragmentViewModel.intent.send(intent)
        }
    }

    private fun handleIntent() {
        launch {
            intent.consumeAsFlow().collect {
                when (it) {
                    Intent.InitLoading -> initContent()
                }
            }
        }
    }

    private fun initContent() {
        Log.d("Sometag", "init")
    }
}