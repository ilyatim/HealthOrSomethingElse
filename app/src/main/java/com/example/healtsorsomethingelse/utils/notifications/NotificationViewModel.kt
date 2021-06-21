package com.example.healtsorsomethingelse.utils.notifications

import androidx.lifecycle.ViewModel
import com.example.healtsorsomethingelse.data.notification.*
import com.example.healtsorsomethingelse.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val repository: NotificationRepository,
    private val repositoryHelper: NotificationRepositoryHelper
) : BaseViewModel() {

    //@Inject lateinit var repository: NotificationRepository
    //@Inject lateinit var repositoryHelper: NotificationRepositoryHelper

    private var _state: MutableStateFlow<UiState> = MutableStateFlow(UiState.Idle)
    val state: StateFlow<UiState>
        get() = _state

    private val actions: Channel<Actions> = Channel(Channel.UNLIMITED)
    private val notifications: MutableList<UserNotification> = mutableListOf()

    init {
        handleActions()
    }

    fun sendAction(action: Actions) {
        launch {
            this@NotificationViewModel.actions.send(action)
        }
    }

    private fun handleActions() {
        launch {
            actions.consumeAsFlow().collect {
                when (it) {
                    Load -> initLoading()
                    is RemoveNotification -> removeNotification(it.id)
                }
            }
        }
    }

    private fun removeNotification(id: String) {
        this@NotificationViewModel.notifications.removeIf { it.id == id }
        val notifications: MutableList<Notifications> = mutableListOf()
        notifications.addAll(this@NotificationViewModel.notifications)
        repositoryHelper.addDateToNotification(notifications)
        _state.value = UiState.Content(notifications)
    }

    private fun initLoading() {
        _state.value = UiState.Loading
        launch {
            _state.value = try {
                this@NotificationViewModel.notifications.clear()
                this@NotificationViewModel.notifications.addAll(repository.getUserNotification().toMutableList())
                val notifications: MutableList<Notifications> = mutableListOf()
                notifications.addAll(this@NotificationViewModel.notifications)
                repositoryHelper.addDateToNotification(notifications)
                UiState.Content(notifications)
            } catch (e: Exception) {
                UiState.Error(repository.getErrorMessage(e.message))
            }
        }
    }
}