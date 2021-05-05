package com.example.healtsorsomethingelse.utils

import com.example.healtsorsomethingelse.data.profile.ProfileRepository
import com.example.healtsorsomethingelse.data.profile.UiAction
import com.example.healtsorsomethingelse.data.profile.UiState
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
class ProfileFragmentViewModel @Inject constructor(private val repo: ProfileRepository) : BaseViewModel() {
    private val _state: MutableStateFlow<UiState> = MutableStateFlow(UiState.Idle)
    val state: StateFlow<UiState>
        get() = _state

    private val action: Channel<UiAction> = Channel(Channel.UNLIMITED)

    init {
        handleIntent()
    }

    fun sendAction(action: UiAction) {
        launch { this@ProfileFragmentViewModel.action.send(action) }
    }

    private fun handleIntent() {
        launch {
            action.consumeAsFlow().collect {
                when (it) {

                }
            }
        }
    }

    private fun initContent() {
        _state.value = UiState.Loading
        launch {
            _state.value = UiState.Content("plug")
        }
    }
}