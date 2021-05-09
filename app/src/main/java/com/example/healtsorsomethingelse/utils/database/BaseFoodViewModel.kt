package com.example.healtsorsomethingelse.utils.database

import com.example.healtsorsomethingelse.data.database.FoodRepository
import com.example.healtsorsomethingelse.data.database.FoodRepositoryImpl
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

abstract class BaseFoodViewModel(protected val repo: FoodRepository) : BaseViewModel() {
    protected val _state: MutableStateFlow<UiState> = MutableStateFlow(UiState.Idle)
    val state: StateFlow<UiState>
        get() = _state

    private val action: Channel<UiAction> = Channel(Channel.UNLIMITED)

    init {
        handleIntent()
    }

    fun sendAction(action: UiAction) {
        launch { this@BaseFoodViewModel.action.send(action) }
    }

    private fun handleIntent() {
        launch {
            action.consumeAsFlow().collect {
                when (it) {
                    UiAction.Loading -> loadContent()
                }
            }
        }
    }

    abstract fun loadContent()
}