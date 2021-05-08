package com.example.healtsorsomethingelse.utils.database

import android.app.Dialog
import com.example.healtsorsomethingelse.data.database.DialogAction
import com.example.healtsorsomethingelse.data.database.DialogRepository
import com.example.healtsorsomethingelse.data.database.DialogUiState
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
class BottomDialogViewModel @Inject constructor(private val repo: DialogRepository): BaseViewModel() {

    private val actions: Channel<DialogAction> = Channel(Channel.UNLIMITED)

    private val _state: MutableStateFlow<DialogUiState> = MutableStateFlow(DialogUiState.Idle)
    val state: StateFlow<DialogUiState>
        get() = _state

    init {
        handleIntent()
    }

    fun sendIntent(action: DialogAction) {
        launch {
            actions.send(action)
        }
    }

    private fun handleIntent() {
        launch {
            actions.consumeAsFlow().collect {
                when (it) {
                    is DialogAction.LoadData -> initLoading(it.id)
                }
            }
        }
    }

    private fun initLoading(id: Int) {
        _state.value = DialogUiState.Loading
        launch {
            _state.value = DialogUiState.Content(repo.getDialogInfoById(id))/*TODO: Impl Handle error*/
        }
    }

}