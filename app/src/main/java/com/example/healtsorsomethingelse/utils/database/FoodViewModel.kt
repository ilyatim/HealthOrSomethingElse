package com.example.healtsorsomethingelse.utils.database

import com.example.healtsorsomethingelse.data.database.FoodRepository
import com.example.healtsorsomethingelse.data.database.UiAction
import com.example.healtsorsomethingelse.data.database.UiState
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
class FoodViewModel @Inject constructor(val repo: FoodRepository) : BaseViewModel() {

    private val _state: MutableStateFlow<UiState> = MutableStateFlow(UiState.Idle)
    val state: StateFlow<UiState>
        get() = _state

    private val action: Channel<UiAction> = Channel(Channel.UNLIMITED)

    init {
        handleIntent()
    }

    fun sendAction(action: UiAction) {
        launch { this@FoodViewModel.action.send(action) }
    }

    private fun handleIntent() {
        launch {
            action.consumeAsFlow().collect {
                when (it) {
                    UiAction.LoadContent -> loadContent()
                }
            }
        }
    }

    private fun loadContent() {
        _state.value = UiState.Loading
        launch {
            _state.value = UiState.Content(repo.getRecipes())
        }
    }
}