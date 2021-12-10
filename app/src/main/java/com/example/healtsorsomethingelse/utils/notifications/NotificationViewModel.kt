package com.example.healtsorsomethingelse.utils.notifications

import androidx.lifecycle.ViewModel
import com.example.healtsorsomethingelse.data.notification.*
import com.example.healtsorsomethingelse.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
) : BaseViewModel<UiState, Actions>(UiState.Idle) {

    private val notifications: MutableList<UserNotification> = mutableListOf()

    private fun removeNotification(id: String) {
        this@NotificationViewModel.notifications.removeIf { it.id == id }
        val notifications: MutableList<Notifications> = mutableListOf()
        notifications.addAll(this@NotificationViewModel.notifications)
        repositoryHelper.addDateToNotification(notifications)
        setState(UiState.Content(notifications))
    }

    private fun initLoading() {
        setState(UiState.Loading)
        CoroutineScope(Dispatchers.IO).launch {
            val state = try {
                this@NotificationViewModel.notifications.clear()
                this@NotificationViewModel.notifications.addAll(repository.getUserNotification().toMutableList())
                val notifications: MutableList<Notifications> = mutableListOf()
                notifications.addAll(this@NotificationViewModel.notifications)
                repositoryHelper.addDateToNotification(notifications)
                UiState.Content(notifications)
            } catch (e: Exception) {
                UiState.Error(repository.getErrorMessage(e.message))
            }
            setState(state)
        }
    }

    override fun collectAction(action: Actions) {
        when (action) {
            Load -> initLoading()
            is RemoveNotification -> removeNotification(action.id)
        }
    }
}