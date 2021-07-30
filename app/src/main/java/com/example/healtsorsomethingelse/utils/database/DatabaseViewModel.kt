package com.example.healtsorsomethingelse.utils.database

import android.util.Log
import com.example.healtsorsomethingelse.data.database.mainScreen.Actions
import com.example.healtsorsomethingelse.data.database.mainScreen.DatabaseRepository
import com.example.healtsorsomethingelse.data.database.mainScreen.UiState
import com.example.healtsorsomethingelse.utils.BaseViewModel
import com.example.healtsorsomethingelse.utils.GoogleNotFountException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.security.spec.ECField
import javax.inject.Inject

@HiltViewModel
class DatabaseViewModel @Inject constructor(private val repo: DatabaseRepository): BaseViewModel() {
    private val TAG: String = "DatabaseViewModelTag"

    private val _state: MutableStateFlow<UiState> = MutableStateFlow(UiState.Idle)
    val state: StateFlow<UiState>
        get() = _state

    private val actions: Channel<Actions> = Channel(Channel.UNLIMITED)

    private var height: Int = 0

    init {
        handleActions()
    }

    fun sendAction(action: Actions) {
        launch {
            actions.send(action)
        }
    }

    private fun handleActions() {
        launch {
            actions.consumeAsFlow().collect {
                when (it) {
                    Actions.LoadContent -> {
                        loadContent()
                    }
                    is Actions.SetBarHeight -> height = it.height
                }
            }
        }
    }

    private fun loadContent() {
        CoroutineScope(Dispatchers.IO).launch {
            _state.value = try {
                UiState.Content(repo.getContent(), height)
            } catch (e: GoogleNotFountException) {
                Log.d(TAG, "google not found exception")
                UiState.Error(e.message)
            } catch (e: Exception) {
                Log.d(TAG, "exception - ${e.message}")
                UiState.Error(e.message ?: "Unknown message")
            }
        }
    }
}