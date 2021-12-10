package com.example.healtsorsomethingelse.utils

import androidx.lifecycle.ViewModel
import com.example.healtsorsomethingelse.data.BaseUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseViewModel<T: BaseUiState, in K>(idleState: T) : ViewModel(),
    CoroutineScope by MainScope() {

    private val uiState: MutableStateFlow<T> = MutableStateFlow(idleState)
    private val actionChannel: Channel<K> = Channel(Channel.UNLIMITED)

    init { subscribeActionsHandler() }

    protected fun setState(state: T) {
        uiState.value = state
    }

    fun getUiState(): StateFlow<T> = uiState.asStateFlow()

    fun sendAction(action: K) {
        launch { actionChannel.send(action) }
    }

    private fun subscribeActionsHandler() {
        launch {
            actionChannel.consumeAsFlow().collect(::collectAction)
        }
    }
    protected abstract fun collectAction(action: K)
}

