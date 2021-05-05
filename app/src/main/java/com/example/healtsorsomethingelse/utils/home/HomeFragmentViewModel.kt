package com.example.healtsorsomethingelse.utils.home

import android.util.Log
import com.example.healtsorsomethingelse.data.home.HomeRepository
import com.example.healtsorsomethingelse.data.home.HomeIntent
import com.example.healtsorsomethingelse.data.home.UiState
import com.example.healtsorsomethingelse.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ActivityScoped
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

    private val intent: Channel<HomeIntent> = Channel(Channel.UNLIMITED)

    init {
        Log.d("Sometag", "init view model")
        handleIntent()
    }

    fun sendIntent(intent: HomeIntent) {
        launch {
            this@HomeFragmentViewModel.intent.send(intent)
        }
    }

    private fun handleIntent() {
        launch {
            intent.consumeAsFlow().collect {
                when (it) {
                    HomeIntent.InitLoading -> initContent()
                }
            }
        }
    }

    private fun initContent() {
        _state.value = UiState.Loading
        launch {
            _state.value = UiState.Content(4, repo.getAllStatistic())
        }
    }
}