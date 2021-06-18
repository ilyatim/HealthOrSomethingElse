package com.example.healtsorsomethingelse.utils.notifications

import androidx.lifecycle.ViewModel
import com.example.healtsorsomethingelse.data.notification.Actions
import com.example.healtsorsomethingelse.data.notification.Idle
import com.example.healtsorsomethingelse.data.notification.UiState
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ViewModelScoped
class NotificationViewModel @Inject constructor() : ViewModel(), CoroutineScope by MainScope() {

    private var _state: MutableStateFlow<UiState> = MutableStateFlow(Idle)
    val state: StateFlow<UiState>
        get() = _state

    private val actions: Channel<Actions> = Channel(Channel.UNLIMITED)

    fun sendAction(action: Actions) {
        launch {
            this@NotificationViewModel.actions.send(action)
        }
    }
}