package com.example.healtsorsomethingelse.utils.database

import com.example.healtsorsomethingelse.data.database.recipes.FoodRepository
import com.example.healtsorsomethingelse.data.database.recipes.UiAction
import com.example.healtsorsomethingelse.data.database.recipes.UiState
import com.example.healtsorsomethingelse.utils.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseFoodViewModel(protected val repo: FoodRepository) : BaseViewModel() {

    protected val stateData: MutableStateFlow<UiState> = MutableStateFlow(UiState.Idle)
    val state = stateData.asStateFlow()

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