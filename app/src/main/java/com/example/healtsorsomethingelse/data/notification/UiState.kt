package com.example.healtsorsomethingelse.data.notification

import com.example.healtsorsomethingelse.data.BaseUiState

sealed class UiState : BaseUiState() {
    object Idle : UiState()
    object Loading : UiState()
    data class Error(val text: String) : UiState()
    data class Content(val notifications: List<Notifications>) : UiState()
}