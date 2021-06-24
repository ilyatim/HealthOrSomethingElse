package com.example.healtsorsomethingelse.utils.database

import com.example.healtsorsomethingelse.data.database.mainScreen.Actions
import com.example.healtsorsomethingelse.data.database.mainScreen.DatabaseRepository
import com.example.healtsorsomethingelse.data.database.mainScreen.UiState
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
class DatabaseViewModel @Inject constructor(private val repo: DatabaseRepository): BaseViewModel() {
    private val _state: MutableStateFlow<UiState> = MutableStateFlow(UiState.Idle)
    val state: StateFlow<UiState>
        get() = _state

    private val actions: Channel<Actions> = Channel(Channel.UNLIMITED)

    init {
        handleActions()
    }

    private fun handleActions() {
        launch {
            actions.consumeAsFlow().collect {
                when (it) {
                    Actions.LoadContent -> {
                        loadContent()
                    }
                }
            }
        }
    }

    private fun loadContent() {
        //
    }
}