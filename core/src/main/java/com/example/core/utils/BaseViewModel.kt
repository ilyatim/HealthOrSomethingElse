package com.example.core.utils

import androidx.lifecycle.ViewModel
import com.example.core.BaseUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * Abstract base view model with MVI Pattern
 * @param T ui state
 * @param K action event
 * @param E ui event action
 */
abstract class BaseViewModel<T, in K, E>(
    protected val scope: CoroutineScope = CoroutineScope(Job() + Dispatchers.Main)
) : ViewModel(), CoroutineScope by scope {

    protected abstract val uiState: MutableStateFlow<T>

    private val actionChannel: Channel<K> = Channel(Channel.UNLIMITED)
    private val eventChannel: Channel<E> = Channel(Channel.UNLIMITED)

    init {
        launch {
            subscribeActionsHandler()
        }
    }

    /**
     * Send ui intent [event]
     */
    protected fun applyUiEvent(event: E) {
        launch {
            eventChannel.send(event)
        }
    }

    /**
     * Return [Flow] to subscribe on ui event's in view
     */
    fun getEventFlow(): Flow<E> = eventChannel.consumeAsFlow()

    /**
     * Return ui state as [StateFlow] to subscribe into your view's
     * @return [StateFlow]
     */
    fun getUiState(): StateFlow<T> = uiState.asStateFlow()

    /**
     * Send MVI Intent [action]
     * @param action new intent
     */
    fun applyAction(action: K) {
        launch {
            actionChannel.send(action)
        }
    }

    /**
     * Set ui state [T]
     * @param state new ui state
     */
    protected fun setState(state: T) {
        uiState.value = state
    }

    /**
     * Send Intent [action]
     * @param action new intent
     */
    private suspend fun sendAction(action: K) {
        actionChannel.send(action)
    }

    /**
     * Subscribe on action's event
     */
    private suspend fun subscribeActionsHandler() {
       actionChannel.consumeAsFlow().collect(::collectAction)
    }

    /**
     * Collect intent from [actionChannel]
     * @param action collected intent
     */
    protected abstract fun collectAction(action: K)
}